package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishCreateValidatorTest {

    @Mock
    WishPersistenceGateway wishPersistence;
    @Mock
    BasicValidator basicValidator;
    @InjectMocks
    WishCreateValidator validator;

    @Test
    void when_listSize_is_less_than_limit_no_errors_should_be_returned() {
        //given
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .product(UUID.randomUUID())
                .build();

        //when
        when(wishPersistence.countByUser(wish.getUser())).thenReturn(19L);
        when(basicValidator.validate(any(),any())).thenReturn(new ArrayList<>());

        //then
        List<ValidationError> validate = validator.validate(wish);

        assertThat(validate).isEqualTo(Collections.emptyList());
    }

    @Test
    void when_listSize_is_higher_than_limit_a_error_should_be_returned() {
        //given
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .product(UUID.randomUUID())
                .build();

        //when
        when(wishPersistence.countByUser(wish.getUser())).thenReturn(20L);
        when(basicValidator.validate(any(),any())).thenReturn(new ArrayList<>());

        //then
        List<ValidationError> validate = validator.validate(wish);

        assertThat(validate.size()).isEqualTo(1);
    }

}