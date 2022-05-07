package com.doughlima.wishlist.domains;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Wish {

    private UUID id;
    private UUID user;
    private UUID product;
    private LocalDate created;
}
