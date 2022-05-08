package com.doughlima.wishlist.gateways.persistence;

import com.doughlima.wishlist.domains.Wish;

import java.util.List;
import java.util.UUID;

public interface WishPersistenceGateway {

    Wish save(Wish wish);

    List<Wish> getAll(UUID userId);

    boolean existsById(UUID userId, UUID productId);

    void deleteById(UUID userId, UUID productId);
}
