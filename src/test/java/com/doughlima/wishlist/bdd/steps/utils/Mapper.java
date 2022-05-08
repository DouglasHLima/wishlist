package com.doughlima.wishlist.bdd.steps.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired
    private ObjectMapper mapper;

    public String createWishRequestJson(String productId) throws JsonProcessingException {
        var request = mapper.createObjectNode();
        request.put("product", productId);
        return mapper.writeValueAsString(request);
    }
}
