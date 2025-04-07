package com.br.equaly.auth.infrastructure.exception;

import com.br.equaly.auth.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EqualyExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString());
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
