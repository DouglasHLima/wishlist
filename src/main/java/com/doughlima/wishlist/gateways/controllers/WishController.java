package com.doughlima.wishlist.gateways.controllers;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.controllers.assemblers.WishModelAssembler;
import com.doughlima.wishlist.gateways.controllers.requests.WishRequest;
import com.doughlima.wishlist.gateways.controllers.responses.TheProductIsOnListResponse;
import com.doughlima.wishlist.gateways.controllers.responses.WishResponse;
import com.doughlima.wishlist.usecases.CreateWish;
import com.doughlima.wishlist.usecases.DeleteWish;
import com.doughlima.wishlist.usecases.ExistsWishById;
import com.doughlima.wishlist.usecases.GetAllWish;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "api/v1/{userId}/wishlist",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class WishController {

    private final CreateWish createWish;
    private final GetAllWish getAllWish;
    private final WishModelAssembler assembler;
    private final ExistsWishById existsWishById;
    private final DeleteWish deleteWish;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WishResponse> createWish(
            @PathVariable("userId") UUID userId,
            @Valid @RequestBody WishRequest wishRequest) {
        Wish wish = wishRequest.toDomain();
        Wish wishSaved = createWish.execute(userId,wish);
        WishResponse response = assembler.toModel(wishSaved);
        return ResponseEntity
                .created(response.getRequiredLink(IanaLinkRelations.COLLECTION).toUri())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<WishResponse>> getAllWishes(
            @PathVariable("userId") UUID userId
    ) {
        List<Wish> result = getAllWish.execute(userId);
        return ResponseEntity.ok(assembler.toCollectionModel(result));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<TheProductIsOnListResponse> hasProductOnWishList(
            @PathVariable("userId") UUID userId,
            @PathVariable("productId") UUID productId
    ) {
        boolean result = existsWishById.execute(userId,productId);
        return ResponseEntity.ok().body(new TheProductIsOnListResponse(result));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteWish(
            @PathVariable("userId") UUID userId,
            @PathVariable("productId") UUID productId
    ) {
        deleteWish.execute(userId, productId);
        return ResponseEntity.noContent().build();
    }



}
