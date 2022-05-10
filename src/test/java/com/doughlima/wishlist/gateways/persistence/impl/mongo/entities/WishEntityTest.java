package com.doughlima.wishlist.gateways.persistence.impl.mongo.entities;

import com.doughlima.wishlist.domains.Wish;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class WishEntityTest {

    @Test
    void create_entity_from_domain_gives_a_entity_object_with_sucess() {
        //given
        Wish wish = Wish.builder().user(UUID.randomUUID()).product(UUID.randomUUID()).build();
        //then
        WishEntity wishEntity = new WishEntity(wish);

        assertThat(wishEntity.getUser()).isEqualTo(wish.getUser());
        assertThat(wishEntity.getProduct()).isEqualTo(wish.getProduct());
        assertThat(wishEntity.getUuid()).isNotNull();
        assertThat(wishEntity.getCreated()).isNotNull();
    }

    @Test
    void create_domain_from_entity_gives_a_domain_object_with_sucess() {
        //given
        Wish wish = Wish.builder().user(UUID.randomUUID()).product(UUID.randomUUID()).build();
        //then
        WishEntity wishEntity = new WishEntity(wish);
        Wish convertedWish = wishEntity.toDomain();

        assertThat(convertedWish.getUser()).isEqualTo(wish.getUser());
        assertThat(convertedWish.getProduct()).isEqualTo(wish.getProduct());
        assertThat(convertedWish.getId()).isNotNull();
        assertThat(convertedWish.getCreated()).isNotNull();
    }

}