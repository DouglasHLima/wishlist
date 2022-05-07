package com.doughlima.wishlist.gateway.persistance;

import com.doughlima.wishlist.domains.Wish;

public interface WishPersistenceGateway {

    Wish save(Wish wish);

}
