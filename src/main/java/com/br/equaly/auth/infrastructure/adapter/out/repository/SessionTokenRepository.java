package com.br.equaly.auth.infrastructure.adapter.out.repository;

import com.br.equaly.auth.infrastructure.entity.SessionTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface SessionTokenRepository extends CrudRepository<SessionTokenEntity, String> {
}
