package com.dev.touristapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "Places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    private ObjectId id;
    private String placeId;
    private String name;
    private String capital;
    private String about;
    private String climate;
    private String history;
    private String time;
    private String food;
    private List<String> img;
    @DocumentReference(collection = "products")
    private List<Product> productIds;
    @DocumentReference(collection = "tourists")
    private List<Tourist> touristIds;
    @DocumentReference(collection = "reviews")
    private List<Review> reviewIds;

}
