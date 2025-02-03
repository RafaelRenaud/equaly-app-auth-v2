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
 * AccountActivationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-02T22:41:10.381441-03:00[America/Sao_Paulo]")
public class AccountActivationRequest {

  private String email;

  private String newPassword;

  public AccountActivationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AccountActivationRequest(String email, String newPassword) {
    this.email = email;
    this.newPassword = newPassword;
  }

  public AccountActivationRequest email(String email) {
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

  public AccountActivationRequest newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  /**
   * The new password to be set for the account.
   * @return newPassword
  */
  @NotNull 
  @Schema(name = "newPassword", description = "The new password to be set for the account.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("newPassword")
  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountActivationRequest accountActivationRequest = (AccountActivationRequest) o;
    return Objects.equals(this.email, accountActivationRequest.email) &&
        Objects.equals(this.newPassword, accountActivationRequest.newPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, newPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountActivationRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    newPassword: ").append("*").append("\n");
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

