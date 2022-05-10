package com.doughlima.wishlist.gateways.controllers.responses;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private List<String> errors;
}
