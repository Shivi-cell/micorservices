package com.user.service.exceptions;

import com.user.service.payload.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.info("ResourceNotFoundException occurred {}", exception.getMessage());
        ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage()).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException apiException) {
        log.info("ApiExceptionException occurred {}", apiException.getMessage());
        ApiResponse apiResponse = ApiResponse.builder().message(apiException.getMessage()).success(true).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
