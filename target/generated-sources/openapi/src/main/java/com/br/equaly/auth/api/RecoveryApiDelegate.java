package com.br.equaly.auth.api;

import com.br.equaly.auth.model.AccountActivationRequest;
import com.br.equaly.auth.model.RecoveryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link RecoveryApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-02T22:41:10.381441-03:00[America/Sao_Paulo]")
public interface RecoveryApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PATCH /recovery/{recovery_id} : Recover Account
     * Recover an account by setting a new password using a recovery code.
     *
     * @param recoveryId Account Recovery Code (required)
     * @param accountActivationRequest  (optional)
     * @return Account recovered successfully (status code 204)
     *         or Invalid request, check the payload or parameters. (status code 400)
     *         or Authentication failed. Invalid credentials. (status code 401)
     *         or A conflict occurred (e.g., resource already exists). (status code 409)
     *         or Recovery code not found (status code 404)
     *         or Internal server error (status code 500)
     * @see RecoveryApi#recoverAccount
     */
    default ResponseEntity<Void> recoverAccount(String recoveryId,
        AccountActivationRequest accountActivationRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /recovery : Generate Recovery Code
     * Generate and send a recovery account code (RAC) to the user&#39;s email.
     *
     * @param recoveryRequest  (optional)
     * @return Request has been accepted and will be processed. (status code 202)
     *         or Invalid request, check the payload or parameters. (status code 400)
     *         or Authentication failed. Invalid credentials. (status code 401)
     *         or User not found (status code 404)
     *         or Internal server error (status code 500)
     * @see RecoveryApi#recovery
     */
    default ResponseEntity<Void> recovery(RecoveryRequest recoveryRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
