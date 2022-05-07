package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateWish {

    private final WishPersistenceGateway wishPersistence;

    public Wish execute(UUID userId, Wish wish) {
        wish.setUser(userId);
        return wishPersistence.save(wish);
    }
}
