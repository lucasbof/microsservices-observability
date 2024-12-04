package com.teste.teste.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("product-collection")
public record Product(
        @Id
        @Field("_id")
        String id,

        @Field("name")
        String name
) {}
