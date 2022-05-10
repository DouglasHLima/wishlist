package com.doughlima.wishlist.gateways.controllers.responses;

import com.doughlima.wishlist.domains.Wish;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Relation(value = "wish", collectionRelation = "wishes")
public class WishResponse extends RepresentationModel<WishResponse> {
    private UUID product;
    private LocalDateTime createdAt;

    public WishResponse(Wish wish) {
        this.product = wish.getProduct();
        this.createdAt = wish.getCreated();
    }
}
