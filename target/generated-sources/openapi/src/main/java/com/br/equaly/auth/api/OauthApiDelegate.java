package com.br.equaly.auth.api;

import com.br.equaly.auth.model.OAuthTokenResponse;
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
 * A delegate to be called by the {@link OauthApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-02T22:41:10.381441-03:00[America/Sao_Paulo]")
public interface OauthApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /oauth/token : User Login
     * Generate an OAuth 2.0 Access Token using Password Grant.
     *
     * @param organizationAlias Organization alias (required)
     * @param username User login (required)
     * @param password User password (required)
     * @return Access Token Response (status code 200)
     *         or Invalid request, check the payload or parameters. (status code 400)
     *         or Authentication failed. Invalid credentials. (status code 401)
     *         or User not found (status code 404)
     *         or Internal server error (status code 500)
     * @see OauthApi#login
     */
    default ResponseEntity<OAuthTokenResponse> login(String organizationAlias,
        String username,
        String password) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"access_token\" : \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMTIzNDU2Iiwicm9sZSI6IkFkbWluIiwidGVuYW50X2NvZGVzIjoiMTIzIiwiaWF0IjoxNTE2MjM5MDIwfQ.C_nNejjfdD2W4Dk1OeK4mJjXX-oLpgA5rmcdaVQw\", \"refresh_token\" : \"a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6\", \"token_type\" : \"Bearer\", \"expires_in\" : 14400 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /oauth/token/refresh : Refresh OAuth 2.0 Access Token
     * Refresh an expired access token using a refresh token.
     *
     * @param refreshToken  (required)
     * @return New Access Token Created (status code 201)
     *         or Invalid request, check the payload or parameters. (status code 400)
     *         or Authentication failed. Invalid credentials. (status code 401)
     *         or Refresh token is invalid or expired (status code 403)
     *         or Too many requests, please try again later (status code 429)
     * @see OauthApi#refreshToken
     */
    default ResponseEntity<OAuthTokenResponse> refreshToken(String refreshToken) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"access_token\" : \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMTIzNDU2Iiwicm9sZSI6IkFkbWluIiwidGVuYW50X2NvZGVzIjoiMTIzIiwiaWF0IjoxNTE2MjM5MDIwfQ.C_nNejjfdD2W4Dk1OeK4mJjXX-oLpgA5rmcdaVQw\", \"refresh_token\" : \"a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6\", \"token_type\" : \"Bearer\", \"expires_in\" : 14400 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
