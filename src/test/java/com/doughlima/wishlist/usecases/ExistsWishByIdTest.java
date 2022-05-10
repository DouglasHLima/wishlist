package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.exceptions.BusinessValidationException;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import com.doughlima.wishlist.usecases.validators.BasicValidator;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExistsWishByIdTest {
    @Mock
    private WishPersistenceGateway persistence;
    @Mock
    private BasicValidator basicValidator;
    @InjectMocks
    private ExistsWishById existsWishById;

    @Test
    void when_inform_product_and_user_that_exists_then_return_true() {
        //given
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        //when
        when(basicValidator.validate(userId,productId))
                .thenReturn(new ArrayList<>());
        when(persistence.existsById(userId,productId))
                .thenReturn(true);
        //then
        boolean result = existsWishById.execute(userId, productId);
        Assertions.assertTrue(result);
    }

    @Test
    void when_inform_product_and_user_that_not_exists_then_return_false() {
        //given
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        //when
        when(basicValidator.validate(userId,productId))
                .thenReturn(new ArrayList<>());
        when(persistence.existsById(userId,productId))
                .thenReturn(false);
        //then
        boolean result = existsWishById.execute(userId, productId);
        Assertions.assertFalse(result);
    }

    @Test
    void when_inform_a_invalid_product_id_then_throw_exception() {
        //given
        UUID userId = UUID.randomUUID();
        //when
        when(basicValidator.validate(userId,null))
                .thenReturn(List.of(ValidationError.builder().build()));
        //then
        assertThrows(BusinessValidationException.class,() -> existsWishById.execute(userId,null));
    }
}