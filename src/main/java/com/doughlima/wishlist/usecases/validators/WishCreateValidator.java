package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.MessageCode;
import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WishCreateValidator {

    private final Integer MAX_SIZE_WISHLIST = 20;
    private final WishPersistenceGateway persistance;
    private final BasicValidator basicValidator;

    public List<ValidationError> validate(Wish wish) {
        List<ValidationError> validationErrors = basicValidator.validate(wish.getUser(),wish.getProduct());

        if (persistance.countByUser(wish.getUser()) >= MAX_SIZE_WISHLIST) {
            validationErrors.add(
                    ValidationError
                            .builder()
                            .keyMessage(MessageCode.MAX_SIZE_CONSTRAINT)
                            .params(List.of(MessageCode.MAX_SIZE_CONSTRAINT_MESSAGE))
                            .build()
            );
        }

        return validationErrors;
    }

}
