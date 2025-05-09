package com.br.equaly.auth.infrastructure.adapter.out.repository;

import com.br.equaly.auth.infrastructure.entity.AppkeyEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppkeyRepository extends CrudRepository<AppkeyEntity, String> {
}
