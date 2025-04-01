package com.br.equaly.auth.infrastructure.adapter.out.repository;

import com.br.equaly.auth.application.port.output.UserCustomPort;
import com.br.equaly.auth.domain.model.Company;
import com.br.equaly.auth.infrastructure.entity.CompanyEntity;
import com.br.equaly.auth.infrastructure.entity.UserEntity;
import com.br.equaly.auth.infrastructure.exception.BusinessException;
import com.br.equaly.auth.infrastructure.mapper.CompanyMapper;
import com.br.equaly.auth.model.CompaniesResponse;
import com.br.equaly.auth.util.ConstantsUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserCustomRepositoryImpl implements UserCustomPort {

    private final EntityManager entityManager;
    private final CompanyMapper companyMapper;
    private final UserRepository userRepository;
    private final MessageSource messageSource;

    public UserCustomRepositoryImpl(EntityManager entityManager, CompanyMapper companyMapper, @Lazy UserRepository userRepository, MessageSource messageSource) {
        this.entityManager = entityManager;
        this.companyMapper = companyMapper;
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    @Override
    public CompaniesResponse findCompniesByUserDocument(String userId, String alias, String document, String status, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompanyEntity> criteriaQuery = criteriaBuilder.createQuery(CompanyEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        Join<UserEntity, CompanyEntity> companyJoin = root.join("companyEntity");

        List<Long> userIds = userRepository.findAllUserIdsActives(userId);

        if(userIds.isEmpty()) {
            throw new BusinessException(messageSource.getMessage("error.user_not_found", null, LocaleContextHolder.getLocale()));
        }

        criteriaQuery.select(companyJoin)
                .where(
                        this.getPredicates(userIds, alias, document, status, criteriaBuilder, root, companyJoin)
                                .toArray(new Predicate[0]))
                .orderBy(criteriaBuilder.asc(companyJoin.get("id")));

        List<CompanyEntity> companyEntities = entityManager
                .createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<UserEntity> countRoot = countQuery.from(UserEntity.class);
        Join<UserEntity, CompanyEntity> countCompanyJoin = countRoot.join("companyEntity");
        countQuery.select(criteriaBuilder.count(countCompanyJoin))
                .where(
                        this.getPredicates(userIds, alias, document, status, criteriaBuilder, countRoot, countCompanyJoin)
                                .toArray(new Predicate[0]));
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        List<Company> companies = companyMapper.toCompany(companyEntities);

        return companyMapper.toCompaniesResponseDTO(new PageImpl<>(companies, pageable, totalCount));
    }

    private List<Predicate> getPredicates(
            List<Long> userIds,
            String alias, String document, String status,
            CriteriaBuilder builder,
            Root<UserEntity> root,
            Join<UserEntity, CompanyEntity> companyJoin) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(root.get("id").in(userIds));

        if (alias != null && !alias.isEmpty()) {
            predicates.add(builder.like(companyJoin.get("alias"), "%" + alias + "%"));
        }
        if (document != null && !document.isEmpty()) {
            predicates.add(builder.equal(companyJoin.get("document"), "%" + document + "%"));
        }
        if (status != null && !status.isEmpty()) {
            predicates.add(builder.equal(companyJoin.get("isActive"),
                    ConstantsUtils.ACTIVE_STATUS.equals(status) ? Boolean.TRUE : Boolean.FALSE));
        }

        return predicates;
    }
}