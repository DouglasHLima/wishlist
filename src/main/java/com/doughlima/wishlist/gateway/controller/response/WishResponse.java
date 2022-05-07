package com.doughlima.wishlist.gateway.controller.response;

import com.doughlima.wishlist.domains.Wish;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
public class WishResponse extends RepresentationModel<WishResponse> {

    private UUID id;
    private UUID user;
    private UUID product;
    private LocalDate createdAt;

    public WishResponse(Wish wish) {
        this.id = wish.getId();
        this.user = wish.getUser();
        this.product = wish.getProduct();
        this.createdAt = wish.getCreated();
    }
}
