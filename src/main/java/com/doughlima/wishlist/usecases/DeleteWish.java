package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.exceptions.BusinessValidationException;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteWish {

    private final WishPersistenceGateway persistence;
    private final ExistsWishById existsWishById;


    public void execute(UUID userId, UUID productId) {
        if (!existsWishById.execute(userId, productId)) {
            ValidationError validationError = ValidationError.builder().build();
            throw new BusinessValidationException(validationError);
        }

        persistence.deleteById(userId, productId);
    }
}
