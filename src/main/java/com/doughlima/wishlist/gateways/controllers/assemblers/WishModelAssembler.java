package com.doughlima.wishlist.gateways.controllers.assemblers;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.controllers.WishController;
import com.doughlima.wishlist.gateways.controllers.responses.WishResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class WishModelAssembler implements RepresentationModelAssembler<Wish, WishResponse> {

    @Override
    public WishResponse toModel(Wish entity) {
        WishResponse response = new WishResponse(entity);
        return response.add(
                linkTo(methodOn(WishController.class).getAllWishes(entity.getUser()))
                        .withRel(IanaLinkRelations.COLLECTION).withType("GET"),
                linkTo(methodOn(WishController.class).deleteWish(entity.getUser(),entity.getProduct()))
                        .withRel("delete").withType("DELETE"),
                linkTo(methodOn(WishController.class).hasProductOnWishList(entity.getUser(),entity.getProduct()))
                        .withRel("is-on-wishlist").withType("GET")
        );
    }

    @Override
    public CollectionModel<WishResponse> toCollectionModel(Iterable<? extends Wish> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
