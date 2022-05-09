package com.doughlima.wishlist.mocks;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Profile("test")
@Component
public class PersistenceGatewayMock implements WishPersistenceGateway {
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

    @Override
    public void deleteById(UUID userId, UUID productId) {
        List<Wish> collect = wishes.stream()
                .filter(wish -> wish.getProduct().equals(productId) && wish.getUser().equals(userId))
                .collect(Collectors.toList());
        wishes.removeAll(collect);
    }

}
