package com.doughlima.wishlist.gateway.controller;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateway.controller.request.WishRequest;
import com.doughlima.wishlist.gateway.controller.response.WishResponse;
import com.doughlima.wishlist.usecases.CreateWish;
import com.doughlima.wishlist.usecases.FindWish;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "api/v1/{userId}/wishlist",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class WishController {

    private final CreateWish createWish;
    private final FindWish findWish;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WishResponse> createWish(
            @PathVariable("userId") UUID userId,
            @Valid @RequestBody WishRequest wishRequest) {
        Wish wish = wishRequest.toDomain();
        Wish wishSaved = createWish.execute(userId,wish);
        WishResponse response = new WishResponse(wishSaved);
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WishController.class).findWish(response.getUser(),response.getProduct())).withSelfRel());
        return ResponseEntity.created(response.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(response);
    }

    @GetMapping(
            value = "/{productId}"
    )
    public ResponseEntity<WishResponse> findWish(
            @PathVariable("userId") UUID userId,
            @PathVariable("productId") UUID productId
    ) {
        Wish result = findWish.execute(userId,productId);
        WishResponse response = new WishResponse(result);
        return ResponseEntity.ok(response);
    }


}
