package com.doughlima.wishlist.gateways.persistence.impl.mongo;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import com.doughlima.wishlist.gateways.persistence.impl.mongo.entities.WishEntity;
import com.doughlima.wishlist.gateways.persistence.impl.mongo.repositories.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class WishPersistenceGatewayImpl implements WishPersistenceGateway {
    private final WishRepository repository;
    @Override
    public Wish save(Wish wish) {
        WishEntity saved = repository.save(new WishEntity(wish));
        return saved.toDomain();
    }
    @Override
    public List<Wish> getAll(UUID userId) {
        List<WishEntity> result = repository.findAllByUser(userId);
        return result.stream().map(WishEntity::toDomain).collect(Collectors.toList());
    }
    @Override
    public boolean existsById(UUID userId, UUID productId) {
        return repository.existsByUserAndProduct(userId, productId);
    }
    @Override
    public void deleteById(UUID userId, UUID productId) {
        repository.deleteByUserAndProduct(userId, productId);
    }

    @Override
    public long countByUser(UUID user) {
        return repository.countByUser(user);
    }


}
