package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.exceptions.BusinessValidationException;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import com.doughlima.wishlist.usecases.validators.BasicValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteWishTest {

    @Mock
    private WishPersistenceGateway persistence;
    @Mock
    private ExistsWishById existsWishById;
    @Mock
    private BasicValidator basicValidator;
    @InjectMocks
    DeleteWish deleteWish;

    @Test
    void when_inform_a_valid_wish_then_delete_with_success() {
        //given
        UUID userId = UUID.randomUUID();
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .product(UUID.randomUUID())
                .build();
        //when
        when(basicValidator.validate(wish.getUser(),wish.getProduct()))
                .thenReturn(new ArrayList<>());
        when(existsWishById.execute(wish.getUser(),wish.getProduct()))
                .thenReturn(true);
        doNothing().when(persistence).deleteById(wish.getUser(),wish.getProduct());
        //then
        deleteWish.execute(wish.getUser(),wish.getProduct());

        verify(persistence, times(1)).deleteById(wish.getUser(),wish.getProduct());
    }

    @Test
    void when_inform_no_product_then_throw_exception() {
        //given
        UUID userId = UUID.randomUUID();
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .build();
        //when
        when(basicValidator.validate(wish.getUser(),wish.getProduct()))
                .thenReturn(List.of(ValidationError.builder().build()));
        //then
        assertThrows(BusinessValidationException.class,() -> deleteWish.execute(wish.getUser(),wish.getProduct()));
    }

    @Test
    void when_inform_a_invalid_product_id_then_throw_exception() {
        //given
        UUID userId = UUID.randomUUID();
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .product(UUID.randomUUID())
                .build();
        //when
        when(basicValidator.validate(wish.getUser(),wish.getProduct()))
                .thenReturn(new ArrayList<>());
        when(existsWishById.execute(wish.getUser(),wish.getProduct()))
                .thenReturn(false);
        //then
        assertThrows(BusinessValidationException.class,() -> deleteWish.execute(wish.getUser(),wish.getProduct()));
    }
}