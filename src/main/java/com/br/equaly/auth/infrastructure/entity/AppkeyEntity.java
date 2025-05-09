package com.br.equaly.auth.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "AppkeyEntity", timeToLive = 86400L)
public class AppkeyEntity implements Serializable {

    @Id
    private String appkey;

    private String secret;

    public AppkeyEntity(String appkey, String secret) {
        this.appkey = appkey;
        this.secret = secret;
    }

    public String getAppkey() {
        return appkey;
    }

    public String getSecret() {
        return secret;
    }
}