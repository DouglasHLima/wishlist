package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
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
                    .keyMessage("user-constraint")
                    .params(List.of("user can't be null"))
                    .build()
            );
        }
        if (product == null) {
            validationErrors.add(
                    ValidationError
                    .builder()
                    .keyMessage("product-constraint")
                    .params(List.of("product can't be null"))
                    .build()
            );
        }
        return validationErrors;
    }

}
