package com.br.equaly.auth.application.service;

import com.br.equaly.auth.application.port.input.UserUseCase;
import com.br.equaly.auth.application.port.output.UserCustomPort;
import com.br.equaly.auth.domain.model.User;
import com.br.equaly.auth.infrastructure.adapter.out.repository.UserRepository;
import com.br.equaly.auth.infrastructure.exception.BusinessException;
import com.br.equaly.auth.infrastructure.mapper.UserMapper;
import com.br.equaly.auth.model.CompaniesResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserUseCase {

    private final UserCustomPort userCustomPort;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private MessageSource messageSource;

    public UserService(UserCustomPort userCustomPort, UserRepository userRepository, UserMapper userMapper, MessageSource messageSource) {
        this.userCustomPort = userCustomPort;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.messageSource = messageSource;
    }

    @Override
    public CompaniesResponse listCompanies(String userId, String alias, String document, String status, Pageable pageable) {
        return userCustomPort.findCompniesByUserDocument(userId, alias, document, status,pageable);
    }

    @Override
    public User getUser(Integer companyId, String login, String email, String document) {
        if(login != null && !login.isEmpty()){
            return this.loadUserByUsername(login);
        }else if(document != null && !document.isEmpty()){
            return this.getUserByDocument(document,companyId.longValue());
        }else if(email != null && !email.isEmpty()){
            return this.getUserByEmail(email, companyId.longValue());
        }else{
            throw new BusinessException(messageSource.getMessage("error.invalid_auth_method", null, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.toUserDTO(userRepository.findById(id).get());
    }

    @Override
    public User loadUserByUsername(String username){
        return userMapper.toUserDTO(userRepository.findByLogin(username).get());
    }

    @Override
    public User getUserByDocument(String document, Long organizationId){
        return userMapper.toUserDTO(userRepository.findByDocument(document, organizationId).get());
    }

    @Override
    public User getUserByEmail(String email, Long organizationId){
        return userMapper.toUserDTO(userRepository.findByEmail(email, organizationId).get());
    }

    @Override
    public void updateUserPassword(User user, String newPassword) {
        userRepository.changePassword(user.getId(), user.getPassword(), newPassword, user.getUsername());
    }
}