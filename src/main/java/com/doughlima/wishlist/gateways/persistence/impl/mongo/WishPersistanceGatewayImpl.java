package com.doughlima.wishlist.gateways.persistence.impl.mongo;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import org.springframework.stereotype.Component;

@Component
public class WishPersistanceGatewayImpl implements WishPersistenceGateway {

    @Override
    public Wish save(Wish wish) {
        return wish;
    }
}
