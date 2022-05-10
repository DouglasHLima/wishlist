package com.doughlima.wishlist.gateways.persistence.impl.mongo.repositories;

import com.doughlima.wishlist.gateways.persistence.impl.mongo.entities.WishEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WishRepository extends MongoRepository<WishEntity, ObjectId> {
    List<WishEntity> findAllByUser(UUID userId);
    boolean existsByUserAndProduct(UUID userId, UUID productId);
    void deleteByUserAndProduct(UUID userId, UUID productId);
    long countByUser(UUID userID);
}
