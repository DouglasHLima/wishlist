package com.doughlima.wishlist.domains;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ValidationError {
    private final MessageCode keyMessage;
    private final List<Object> params;
}
