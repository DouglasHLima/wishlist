package com.doughlima.wishlist.gateways.persistence;

import com.doughlima.wishlist.domains.Wish;

public interface WishPersistenceGateway {

    Wish save(Wish wish);

}
