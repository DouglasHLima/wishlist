package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasicValidatorTest {
    BasicValidator validator = new BasicValidator();

    @Test
    void when_user_is_null_than_a_error_should_be_returned() {
        //given
        Wish wish = Wish.builder()
                .product(UUID.randomUUID())
                .build();
        //then
        List<ValidationError> validate = validator.validate(wish.getUser(),wish.getProduct());
        assertThat(validate.size()).isEqualTo(1);
    }
    @Test
    void when_product_is_null_than_a_error_should_be_returned() {
        //given
        Wish wish = Wish.builder()
                .user(UUID.randomUUID())
                .build();
        //then
        List<ValidationError> validate = validator.validate(wish.getUser(),wish.getProduct());
        assertThat(validate.size()).isEqualTo(1);
    }

    @Test
    void when_product_and_user_are_null_than_two_errors_should_be_returned() {
        //given
        Wish wish = Wish.builder().build();
        //then
        List<ValidationError> validate = validator.validate(wish.getUser(),wish.getProduct());
        assertThat(validate.size()).isEqualTo(2);
    }
}