package com.br.equaly.auth.api;

import com.br.equaly.auth.model.AccountActivationRequest;
import com.br.equaly.auth.model.RecoveryRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-02T22:41:10.381441-03:00[America/Sao_Paulo]")
@Controller
@RequestMapping("${openapi.eQualyAuthenticationService.base-path:/authentication/v2}")
public class RecoveryApiController implements RecoveryApi {

    private final RecoveryApiDelegate delegate;

    public RecoveryApiController(@Autowired(required = false) RecoveryApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RecoveryApiDelegate() {});
    }

    @Override
    public RecoveryApiDelegate getDelegate() {
        return delegate;
    }

}
