package com.doughlima.wishlist.gateway.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter @Setter
public class WishRequest {
    @NotNull(message = "not be null")
    private UUID user;
    @NotNull(message = "not be null")
    private UUID product;
}
