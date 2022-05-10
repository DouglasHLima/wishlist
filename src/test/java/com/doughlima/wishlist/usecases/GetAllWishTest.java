package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllWishTest {

    @Mock
    private WishPersistenceGateway persistence;
    @InjectMocks
    private GetAllWish getAllWish;

    @Test
    void return_list_of_wishes_on_wishlist() {
        //given
        UUID userId = UUID.randomUUID();
        //when
        when(persistence.getAll(userId)).thenReturn(List.of(Wish.builder().build()));
        //then
        List<Wish> result = getAllWish.execute(userId);
        Assertions.assertThat(result).isInstanceOf(List.class);
    }
}