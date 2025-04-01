package com.br.equaly.auth.infrastructure.adapter.out.repository;

import com.br.equaly.auth.infrastructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("""
    SELECT user.id FROM UserEntity user WHERE user.universalUserEntity.documentNumber = :document 
    AND user.isActive AND user.lastLogin IS NOT NULL
""")
    List<Long> findAllUserIdsActives(String document);

    Optional<UserEntity> findByLogin(String login);

    @Query("""
    SELECT user FROM UserEntity user WHERE user.email = :email
    AND user.companyEntity.id = :organizationId
""")
    Optional<UserEntity> findByEmail(String email, Long organizationId);

    @Query("""
    SELECT user FROM UserEntity user WHERE user.universalUserEntity.documentNumber = :document
    AND user.companyEntity.id = :organizationId
""")
    Optional<UserEntity> findByDocument(String document, Long organizationId);

    Optional<UserEntity> findById(Long id);

    @Modifying
    @Transactional
    @Query("""
    UPDATE UserEntity user SET user.password = :newPassword WHERE user.id = :id AND user.password = :password
""")
    void changePassword(Long id, String password, String newPassword);
}