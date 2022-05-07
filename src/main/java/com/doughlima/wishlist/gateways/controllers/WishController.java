package com.doughlima.wishlist.gateways.controllers;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.controllers.assemblers.WishModelAssembler;
import com.doughlima.wishlist.gateways.controllers.requests.WishRequest;
import com.doughlima.wishlist.gateways.controllers.responses.WishResponse;
import com.doughlima.wishlist.usecases.CreateWish;
import com.doughlima.wishlist.usecases.FindWish;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "api/v1/{userId}/wishlist",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class WishController {

    private final CreateWish createWish;
    private final FindWish findWish;
    private final WishModelAssembler assembler;

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
        List<Wish> result = findWish.execute(userId);
        return ResponseEntity.ok(assembler.toCollectionModel(result));
    }


}
