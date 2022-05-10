package com.doughlima.wishlist.gateways.persistence.impl.mongo;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.impl.mongo.entities.WishEntity;
import com.doughlima.wishlist.gateways.persistence.impl.mongo.repositories.WishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WishPersistenceGatewayImplTest {

    @Mock
    private WishRepository repository;

    @InjectMocks
    private WishPersistenceGatewayImpl wishPersistenceGateway;

    @Test
    void when_inform_a_wish_to_save_then_return_it() {
        //given
        Wish wishToSave = Wish.builder().user(UUID.randomUUID()).product(UUID.randomUUID()).build();
        //when
        Mockito.when(repository.save(any())).thenReturn(new WishEntity(wishToSave));
        //then
        Wish saved = wishPersistenceGateway.save(wishToSave);

        assertThat(saved.getUser()).isEqualTo(wishToSave.getUser());
        assertThat(saved.getProduct()).isEqualTo(wishToSave.getProduct());
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreated()).isNotNull();
    }

    @Test
    void when_require_all_wishes_of_user_then_return_the_list() {
        //given
        UUID userID = UUID.randomUUID();
        //when
        Mockito.when(repository.findAllByUser(userID)).thenReturn(List.of(new WishEntity(Wish.builder().build())));
        //then
        List<Wish> result = wishPersistenceGateway.getAll(userID);

        assertThat(result).isInstanceOf(List.class);
    }
    @Test
    void when_require_exists_by_id_a_existent_item_return_true() {
        //given
        UUID userID = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        //when
        Mockito.when(repository.existsByUserAndProduct(userID,productId)).thenReturn(true);
        //then
        boolean result = wishPersistenceGateway.existsById(userID,productId);

        assertThat(result).isTrue();
    }
    @Test
    void when_require_exists_by_id_a_inexistent_item_return_false() {
        //given
        UUID userID = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        //when
        Mockito.when(repository.existsByUserAndProduct(userID,productId)).thenReturn(false);
        //then
        boolean result = wishPersistenceGateway.existsById(userID,productId);

        assertThat(result).isFalse();
    }
}