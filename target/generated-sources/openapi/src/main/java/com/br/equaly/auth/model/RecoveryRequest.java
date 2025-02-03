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
 * RecoveryRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-02T22:41:10.381441-03:00[America/Sao_Paulo]")
public class RecoveryRequest {

  private String organizationAlias;

  private String email;

  private String login;

  public RecoveryRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RecoveryRequest(String organizationAlias, String email, String login) {
    this.organizationAlias = organizationAlias;
    this.email = email;
    this.login = login;
  }

  public RecoveryRequest organizationAlias(String organizationAlias) {
    this.organizationAlias = organizationAlias;
    return this;
  }

  /**
   * Alias of the organization the user belongs to.
   * @return organizationAlias
  */
  @NotNull 
  @Schema(name = "organizationAlias", example = "companyX", description = "Alias of the organization the user belongs to.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("organizationAlias")
  public String getOrganizationAlias() {
    return organizationAlias;
  }

  public void setOrganizationAlias(String organizationAlias) {
    this.organizationAlias = organizationAlias;
  }

  public RecoveryRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @NotNull @jakarta.validation.constraints.Email 
  @Schema(name = "email", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public RecoveryRequest login(String login) {
    this.login = login;
    return this;
  }

  /**
   * Get login
   * @return login
  */
  @NotNull 
  @Schema(name = "login", example = "login123", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("login")
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecoveryRequest recoveryRequest = (RecoveryRequest) o;
    return Objects.equals(this.organizationAlias, recoveryRequest.organizationAlias) &&
        Objects.equals(this.email, recoveryRequest.email) &&
        Objects.equals(this.login, recoveryRequest.login);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organizationAlias, email, login);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecoveryRequest {\n");
    sb.append("    organizationAlias: ").append(toIndentedString(organizationAlias)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
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

