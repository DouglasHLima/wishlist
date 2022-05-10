package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.exceptions.BusinessValidationException;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import com.doughlima.wishlist.usecases.validators.BasicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExistsWishById {

    private final BasicValidator basicValidator;
    private final WishPersistenceGateway persistence;
    public boolean execute(UUID userId, UUID productId) {
        List<ValidationError> validate = basicValidator.validate(userId, productId);
        if (!validate.isEmpty()) {
            ValidationError validationError = ValidationError.builder().build();
            throw new BusinessValidationException(validationError);
        }
        return persistence.existsById(userId,productId);
    }
}
