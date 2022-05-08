package com.doughlima.wishlist.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ValidationError {
    private final String keyMessage;
    private final List<Object> params;
}
