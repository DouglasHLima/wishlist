package com.doughlima.wishlist.gateway.persistance.impl.mongo;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateway.persistance.WishPersistenceGateway;
import org.springframework.stereotype.Component;

@Component
public class WishPersistanceGatewayImpl implements WishPersistenceGateway {

    @Override
    public Wish save(Wish wish) {
        return wish;
    }
}
