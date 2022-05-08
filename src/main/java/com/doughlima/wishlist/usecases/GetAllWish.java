package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllWish {

    private final WishPersistenceGateway persistence;
    public List<Wish> execute(UUID userId) {
        return persistence.getAll(userId);
    }
}
