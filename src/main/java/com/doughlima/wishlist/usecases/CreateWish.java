package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.exceptions.BusinessValidationException;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import com.doughlima.wishlist.usecases.validators.CreateWishValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateWish {

    private final WishPersistenceGateway wishPersistence;
    private final CreateWishValidator validator;

    public Wish execute(UUID userId, Wish wish) {
        wish.setUser(userId);
        val validationErrors = validator.validate(wish);

        if (!validationErrors.isEmpty()) {
            throw new BusinessValidationException(validationErrors);
        }

        return wishPersistence.save(wish);
    }
}
