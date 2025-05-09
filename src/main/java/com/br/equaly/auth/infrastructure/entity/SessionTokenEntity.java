package com.br.equaly.auth.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "SessionTokenEntity", timeToLive = 14400L)
public class SessionTokenEntity  implements Serializable {

    @Id
    private String id;

    private String tokenHash;

    public SessionTokenEntity(String id, String tokenHash) {
        this.id = id;
        this.tokenHash = tokenHash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }
}