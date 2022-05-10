package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.MessageCode;
import com.doughlima.wishlist.domains.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BasicValidator {


    public List<ValidationError> validate(UUID user, UUID product) {
        List<ValidationError> validationErrors = new ArrayList<>();

        if (user == null) {
            validationErrors.add(
                    ValidationError
                    .builder()
                    .keyMessage(MessageCode.REQUIRED_FIELD)
                    .params(List.of(MessageCode.USER_FIELD))
                    .build()
            );
        }
        if (product == null) {
            validationErrors.add(
                    ValidationError
                    .builder()
                    .keyMessage(MessageCode.REQUIRED_FIELD)
                    .params(List.of(MessageCode.PRODUCT_FIELD))
                    .build()
            );
        }
        return validationErrors;
    }

}
