package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.exceptions.BusinessValidationException;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import com.doughlima.wishlist.usecases.validators.WishCreateValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CreateWishTest {

    @Mock
    private WishPersistenceGateway wishPersistence;
    @Mock
    private WishCreateValidator validator;
    @InjectMocks
    private CreateWish createWish;

    @Test
    void when_inform_a_valid_wish_then_save_with_success() {
        //given
        UUID userId = UUID.randomUUID();
        Wish wish = Wish.builder()
                .product(UUID.randomUUID())
                .build();
        //when
        when(validator.validate(wish)).thenReturn(new ArrayList<>());
        when(wishPersistence.save(wish)).thenReturn(wish);
        //then
        Wish result = createWish.execute(userId, wish);

        assertThat(result.getUser()).isEqualTo(userId);
        assertThat(result.getProduct()).isEqualTo(wish.getProduct());

    }
    @Test
    void when_inform_a_invalid_wish_then_throw_exception() {
        //given
        UUID userId = UUID.randomUUID();
        Wish wish = Wish.builder()
                .build();
        //when
        when(validator.validate(wish)).thenReturn(List.of(ValidationError.builder().build()));
        //then
        assertThrows(BusinessValidationException.class,() -> createWish.execute(userId, wish));
    }
}