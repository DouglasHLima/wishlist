package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WishListSizeValidator {

    private final Integer MAX_SIZE_WISHLIST = 20;
    private final WishPersistenceGateway persistance;

    public List<ValidationError> validate(Wish wish) {
        List<ValidationError> validationErrors = new ArrayList<>();
        long wishQuantity = persistance.countByUser(wish.getUser());
        if (wishQuantity >= MAX_SIZE_WISHLIST) {
            validationErrors.add(
                    ValidationError
                            .builder()
                            .keyMessage("size-constraint")
                            .params(List.of("max size of wishlist is "+ MAX_SIZE_WISHLIST))
                            .build()
            );
        }
        return validationErrors;
    }

}
