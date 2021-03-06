package com.doughlima.wishlist.gateways.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductIsOnListResponse {
    @JsonProperty("is-present")
    private Boolean isPresent;
}
