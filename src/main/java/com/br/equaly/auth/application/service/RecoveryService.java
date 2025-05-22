package com.br.equaly.auth.application.service;

import com.br.equaly.auth.application.port.input.RecoveryUseCase;
import com.br.equaly.auth.application.port.input.UserUseCase;
import com.br.equaly.auth.application.port.output.RecoveryQueuePort;
import com.br.equaly.auth.domain.enums.RecoveryTokenType;
import com.br.equaly.auth.domain.model.User;
import com.br.equaly.auth.infrastructure.adapter.out.repository.RecoveryTokenRepository;
import com.br.equaly.auth.infrastructure.entity.RecoveryTokenEntity;
import com.br.equaly.auth.infrastructure.exception.BusinessException;
import com.br.equaly.auth.model.AccountActivationRequest;
import com.br.equaly.auth.model.RecoveryRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class RecoveryService implements RecoveryUseCase {

    private UserUseCase userUseCase;
    private final MessageSource messageSource;
    private final RecoveryTokenRepository recoveryTokenRepository;
    private final RecoveryQueuePort recoveryQueuePort;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public RecoveryService(UserUseCase userUseCase, RecoveryTokenRepository recoveryTokenRepository, RecoveryQueuePort recoveryQueuePort, MessageSource messageSource) {
        this.userUseCase = userUseCase;
        this.recoveryTokenRepository = recoveryTokenRepository;
        this.messageSource = messageSource;
        this.recoveryQueuePort = recoveryQueuePort;
    }

    @Override
    public void sendRecoveryToken(RecoveryRequest recoveryRequest) {
        User user = userUseCase.loadUserByUsername(recoveryRequest.getLogin());

        if (user != null
                && user.getIsActive()
                && user.getCompany().getIsActive()
                && recoveryRequest.getCompanyAlias().equals(user.getCompany().getAlias())
                && user.getDepartment().getIsActive()) {
            RecoveryTokenEntity recoveryToken = new RecoveryTokenEntity(
                    Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)),
                    RecoveryTokenType.RAC_RECOVERY,
                    this.generateRAC(),
                    user.getId(),
                    user.getEmail(),
                    user.getUniversalUser().getName(),
                    user.getUsername(),
                    user.getCompany().getName(),
                    user.getCompany().getTradingName(),
                    user.getCompany().getAlias(),
                    LocalDateTime.now()
            );

            recoveryTokenRepository.save(recoveryToken);
            recoveryQueuePort.sendRecoveryEmail(recoveryToken);
        }
    }

    @Override
    public void recoverAccount(String recoveryId, AccountActivationRequest accountActivationRequest) {
        Optional<RecoveryTokenEntity> recoveryToken = recoveryTokenRepository.findById(recoveryId);

        if (!recoveryToken.isPresent()) {
            throw new BusinessException(messageSource.getMessage("error.generic", null, LocaleContextHolder.getLocale()));
        }

        User user = userUseCase.getUserById(recoveryToken.get().getUserId());

        if(user != null && user.getIsActive()
                && user.getCompany().getIsActive()
                && user.getDepartment().getIsActive()
                && recoveryToken.get().getCode().equals(accountActivationRequest.getRac())
                && accountActivationRequest.getEmail().equals(user.getEmail())){
            this.changePassword(user, accountActivationRequest.getNewPassword());
            recoveryTokenRepository.deleteById(recoveryId);
            recoveryQueuePort.sendRecoveryEmail(
                    new RecoveryTokenEntity(
                            Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)),
                            RecoveryTokenType.ACCOUNT_RECOVERY,
                            null,
                            user.getId(),
                            user.getEmail(),
                            user.getUniversalUser().getName(),
                            user.getUsername(),
                            user.getCompany().getName(),
                            user.getCompany().getTradingName(),
                            user.getCompany().getAlias(),
                            LocalDateTime.now()
                    )
            );
        }
    }

    public void changePassword(User user, String newPassword){
        if(newPassword.length() < 8
                || newPassword.length() > 16
                || bCryptPasswordEncoder.matches(newPassword, user.getPassword())
                || newPassword.contains(" ")){
            throw new BusinessException(messageSource.getMessage("error.invalid_new_password", null, LocaleContextHolder.getLocale()));
        }
        userUseCase.updateUserPassword(user, bCryptPasswordEncoder.encode(newPassword));
    }

    public String generateRAC() {
        Random random = new Random();
        Integer rac = random.nextInt(1000000);
        return String.format("%06d", rac);
    }
}