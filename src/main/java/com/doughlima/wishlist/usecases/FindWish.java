package com.doughlima.wishlist.usecases;

import com.doughlima.wishlist.domains.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindWish {


    public List<Wish> execute(UUID userId) {
        return null;
    }
}
