package com.doughlima.wishlist.usecases.validators;

import com.doughlima.wishlist.domains.ValidationError;
import com.doughlima.wishlist.domains.Wish;
import com.doughlima.wishlist.gateways.persistence.WishPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateWishValidator {

    private final Integer MAX_SIZE_WISHLIST = 20;
    private final WishPersistenceGateway persistance;

    public List<ValidationError> validate(Wish wish) {
        List<ValidationError> validationErrors = new ArrayList<>();
        List<Wish> wishList = persistance.getAll(wish.getUser());
        if (wishList.size() >= MAX_SIZE_WISHLIST) {
            validationErrors.add(
                    ValidationError
                            .builder()
                            .keyMessage("size-constraint")
                            .params(List.of("max size of wishlist is "+ MAX_SIZE_WISHLIST))
                            .build()
            );
        }
        return validationErrors;
    }

}
