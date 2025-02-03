package com.br.equaly.auth.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OAuthTokenResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-02T22:41:10.381441-03:00[America/Sao_Paulo]")
public class OAuthTokenResponse {

  private String accessToken;

  private String tokenType;

  private Integer expiresIn;

  private String refreshToken;

  public OAuthTokenResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OAuthTokenResponse(String accessToken, String tokenType, Integer expiresIn) {
    this.accessToken = accessToken;
    this.tokenType = tokenType;
    this.expiresIn = expiresIn;
  }

  public OAuthTokenResponse accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  /**
   * OAuth 2.0 Access Token
   * @return accessToken
  */
  @NotNull 
  @Schema(name = "access_token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMTIzNDU2Iiwicm9sZSI6IkFkbWluIiwidGVuYW50X2NvZGVzIjoiMTIzIiwiaWF0IjoxNTE2MjM5MDIwfQ.C_nNejjfdD2W4Dk1OeK4mJjXX-oLpgA5rmcdaVQw", description = "OAuth 2.0 Access Token", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("access_token")
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public OAuthTokenResponse tokenType(String tokenType) {
    this.tokenType = tokenType;
    return this;
  }

  /**
   * Get tokenType
   * @return tokenType
  */
  @NotNull 
  @Schema(name = "token_type", example = "Bearer", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("token_type")
  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public OAuthTokenResponse expiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
    return this;
  }

  /**
   * Get expiresIn
   * @return expiresIn
  */
  @NotNull 
  @Schema(name = "expires_in", example = "14400", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("expires_in")
  public Integer getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
  }

  public OAuthTokenResponse refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  /**
   * Token used to renew the access token
   * @return refreshToken
  */
  
  @Schema(name = "refresh_token", example = "a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6", description = "Token used to renew the access token", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("refresh_token")
  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OAuthTokenResponse oauthTokenResponse = (OAuthTokenResponse) o;
    return Objects.equals(this.accessToken, oauthTokenResponse.accessToken) &&
        Objects.equals(this.tokenType, oauthTokenResponse.tokenType) &&
        Objects.equals(this.expiresIn, oauthTokenResponse.expiresIn) &&
        Objects.equals(this.refreshToken, oauthTokenResponse.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, tokenType, expiresIn, refreshToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OAuthTokenResponse {\n");
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
    sb.append("    expiresIn: ").append(toIndentedString(expiresIn)).append("\n");
    sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

