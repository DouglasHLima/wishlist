package com.doughlima.wishlist.domains;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationError {
    private final String keyMessage;
    private final List<Object> params;
}
