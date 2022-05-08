package com.doughlima.wishlist.gateways.controllers.responses;

import com.doughlima.wishlist.domains.Wish;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@Relation(value = "wish", collectionRelation = "wishes")
public class WishResponse extends RepresentationModel<WishResponse> {

    private UUID product;
    private LocalDate createdAt;

    public WishResponse(Wish wish) {
        this.product = wish.getProduct();
        this.createdAt = wish.getCreated();
    }
}
