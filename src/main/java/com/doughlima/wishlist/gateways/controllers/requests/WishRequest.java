package com.doughlima.wishlist.gateways.controllers.requests;

import com.doughlima.wishlist.domains.Wish;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter @Setter
public class WishRequest {

    @ApiModelProperty(required = true, notes = "Product Id", example = "4d2e1274-16ac-40b6-a6cd-9cfc0f05303d")
    @NotNull
    private UUID product;

    public Wish toDomain() {
        return Wish.builder()
                .product(this.product)
                .build();
    }
}
