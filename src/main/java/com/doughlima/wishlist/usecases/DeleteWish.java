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
public class DeleteWish {

    private final WishPersistenceGateway persistence;
    private final ExistsWishById existsWishById;
    private final BasicValidator basicValidator;

    public void execute(UUID userId, UUID productId) {
        List<ValidationError> validationErrors = basicValidator.validate(userId, productId);
        if (!validationErrors.isEmpty()) {
            throw new BusinessValidationException(validationErrors);
        }
        if (!existsWishById.execute(userId, productId)) {
            ValidationError validationError = ValidationError.builder().build();
            throw new BusinessValidationException(validationError);
        }
        persistence.deleteById(userId, productId);
    }
}
