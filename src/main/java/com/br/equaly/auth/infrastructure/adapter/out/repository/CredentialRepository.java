package com.br.equaly.auth.infrastructure.adapter.out.repository;

import com.br.equaly.auth.infrastructure.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    @Query("""
    SELECT credential FROM Credential credential WHERE credential.value = :appkey 
    AND credential.isActive
""")
    CredentialEntity findByAppkey(String appkey);
}