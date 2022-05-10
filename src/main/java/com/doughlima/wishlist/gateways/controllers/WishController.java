package com.doughlima.wishlist.gateways.controllers;

import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.controllers.assemblers.WishModelAssembler;
import com.doughlima.wishlist.gateways.controllers.requests.WishRequest;
import com.doughlima.wishlist.gateways.controllers.responses.ErrorResponse;
import com.doughlima.wishlist.gateways.controllers.responses.ProductIsOnListResponse;
import com.doughlima.wishlist.gateways.controllers.responses.WishResponse;
import com.doughlima.wishlist.usecases.CreateWish;
import com.doughlima.wishlist.usecases.DeleteWish;
import com.doughlima.wishlist.usecases.ExistsWishById;
import com.doughlima.wishlist.usecases.GetAllWish;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
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

    @ApiOperation(value = "Add a wish on wishlist")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Add with success",response = WishResponse.class),
            @ApiResponse(code = 400, message = "When make a Bad Request with invalid parameters", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "when try to add more items than allowed", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WishResponse> createWish(
            @PathVariable("userId") UUID userId,
            @Valid @RequestBody WishRequest wishRequest
    ){
        Wish wish = wishRequest.toDomain();
        Wish wishSaved = createWish.execute(userId,wish);
        WishResponse response = assembler.toModel(wishSaved);
        return ResponseEntity
                .created(response.getRequiredLink(IanaLinkRelations.COLLECTION).toUri())
                .body(response);
    }

    @ApiOperation(value = "Get the complete WishList")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve full list")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<WishResponse>> getAllWishes(
            @PathVariable("userId") UUID userId
    ) {
        List<Wish> result = getAllWish.execute(userId);
        return ResponseEntity.ok(assembler.toCollectionModel(result));
    }

    @ApiOperation(value = "Get if a specific product is on wishlist of a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve if exists", response = ProductIsOnListResponse.class)
    })
    @GetMapping("/{productId}")
    public ResponseEntity<ProductIsOnListResponse> hasProductOnWishList(
            @PathVariable("userId") UUID userId,
            @PathVariable("productId") UUID productId
    ) {
        boolean result = existsWishById.execute(userId,productId);
        return ResponseEntity.ok().body(new ProductIsOnListResponse(result));
    }

    @ApiOperation(value = "delete a wish on wishlist")
    @ApiResponses( value = {
            @ApiResponse(code = 204, message = "No content when delete with success"),
            @ApiResponse(code = 400, message = "When make a Bad Request with invalid parameters", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "when try to delete a existent item", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteWish(
            @PathVariable("userId") UUID userId,
            @PathVariable("productId") UUID productId
    ) {
        deleteWish.execute(userId, productId);
        return ResponseEntity.noContent().build();
    }

}
