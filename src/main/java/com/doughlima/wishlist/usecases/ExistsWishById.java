package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExistsWishById {

    private final WishPersistenceGateway persistence;

    public boolean execute(UUID userId, UUID productId) {
        return persistence.existsById(userId,productId);
    }
}
