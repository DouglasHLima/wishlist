package com.doughlima.wishlist.exceptions;

import com.doughlima.wishlist.domains.ValidationError;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessValidationException extends RuntimeException {

    @Getter
    private final List<ValidationError> validationErrors;
    public BusinessValidationException(List<ValidationError> validationErrors) {
        super(validationErrors.stream().map(ValidationError::toString).collect(Collectors.joining("; ")));
        this.validationErrors = validationErrors;
    }

    public BusinessValidationException(ValidationError validationError) {
        super(validationError.toString());
        this.validationErrors = List.of(validationError);
    }
}
