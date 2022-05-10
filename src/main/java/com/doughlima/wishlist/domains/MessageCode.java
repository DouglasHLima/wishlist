package com.doughlima.wishlist.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageCode {

    REQUIRED_FIELD("required.field"),
    USER_FIELD("user.field"),
    PRODUCT_FIELD("product.field"),
    MAX_SIZE_CONSTRAINT("max.size.constraint"),
    MAX_SIZE_CONSTRAINT_MESSAGE("max.size.constraint.message"),
    RESOURCE_NOT_EXISTS("resources.not.exists"),
    RESOURCE_NOT_EXISTS_USER_PRODUCT("resources.not.exists.user.product");


    private final String code;
}
