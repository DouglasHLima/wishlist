package com.doughlima.wishlist.gateways.persistence.impl.mongo;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class WishPersistanceGatewayImpl implements WishPersistenceGateway {

    private final List<Wish> wishes = new ArrayList<>();
    @Override
    public Wish save(Wish wish) {
        wishes.add(wish);
        return wish;
    }

    @Override
    public List<Wish> getAll(UUID userId) {
        return wishes;
    }

    @Override
    public boolean existsById(UUID userId, UUID productId) {
        return wishes.stream().anyMatch((wish -> wish.getProduct().equals(productId) && wish.getUser().equals(userId)));
    }
}
