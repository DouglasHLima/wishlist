package com.doughlima.wishlist.gateways.persistence.impl.mongo.entities;

import com.doughlima.wishlist.domains.Wish;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "wish")
@NoArgsConstructor
public class WishEntity {

    @Id
    private ObjectId id;
    @Field(targetType = FieldType.STRING)
    private UUID uuid = UUID.randomUUID();
    @Field(targetType = FieldType.STRING)
    private UUID user;
    @Field(targetType = FieldType.STRING)
    private UUID product;
    @Field(targetType = FieldType.STRING)
    private LocalDateTime created = LocalDateTime.now();

    public WishEntity(Wish wish) {
        this.user = wish.getUser();
        this.product = wish.getProduct();
    }

    public Wish toDomain() {
        return Wish.builder()
                .id(this.getUuid())
                .user(this.getUser())
                .product(this.getProduct())
                .created(this.getCreated())
                .build();
    }
}
