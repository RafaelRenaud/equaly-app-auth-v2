package com.br.equaly.auth.infrastructure.adapter.out.repository;

import com.br.equaly.auth.infrastructure.entity.RecoveryTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecoveryTokenRepository extends CrudRepository<RecoveryTokenEntity, String> {
}
