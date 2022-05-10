package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WishListSizeValidatorTest {

    @Mock
    WishPersistenceGateway wishPersistence;
    @InjectMocks
    WishListSizeValidator validator;

    @Test
    void when_listSize_is_less_than_limit_no_errors_should_be_returned() {
        //given
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .product(UUID.randomUUID())
                .build();

        //when
        Mockito.when(wishPersistence.countByUser(wish.getUser())).thenReturn(19L);

        //then
        List<ValidationError> validate = validator.validate(wish);

        Assertions.assertThat(validate).isEqualTo(Collections.emptyList());
    }

    @Test
    void when_listSize_is_higher_than_limit_a_error_should_be_returned() {
        //given
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .product(UUID.randomUUID())
                .build();

        //when
        Mockito.when(wishPersistence.countByUser(wish.getUser())).thenReturn(20L);

        //then
        List<ValidationError> validate = validator.validate(wish);

        Assertions.assertThat(validate.size()).isEqualTo(1);
    }
}