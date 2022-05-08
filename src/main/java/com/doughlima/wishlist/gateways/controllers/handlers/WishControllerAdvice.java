package com.doughlima.wishlist.gateways.controllers.handlers;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.exceptions.BusinessValidationException;
import com.doughlima.wishlist.gateways.controllers.responses.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class WishControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        val errorMessages = exception.getFieldErrors()
                .stream()
                .map(this::mapToErrorMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ErrorResponse(errorMessages));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBusinessValidationException(BusinessValidationException exception) {
        val errorMessages = exception.getValidationErrors()
                .stream()
                .map(ValidationError::toString)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ErrorResponse(errorMessages));
    }

    private String mapToErrorMessage(FieldError fieldError) {
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
    }
}
