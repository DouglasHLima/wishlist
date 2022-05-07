package com.doughlima.wishlist.gateway.controller.request;

import com.doughlima.wishlist.domains.Wish;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter @Setter
public class WishRequest {
    @NotNull
    private UUID product;

    public Wish toDomain() {
        return Wish.builder()
                .product(this.product)
                .build();
    }
}
