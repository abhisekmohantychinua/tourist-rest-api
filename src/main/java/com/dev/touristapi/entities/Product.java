package com.dev.touristapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private String image;
    private String category;


    //all attribute constructor except id
    public Product(String name, String description, String image, String category) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
    }
}
