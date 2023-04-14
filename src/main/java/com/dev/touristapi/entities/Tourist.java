package com.dev.touristapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tourists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tourist {
    @Id
    private ObjectId id;
    private String name;
    private String info;
    private String images;
    private String location;

    //all args constructor except id
    public Tourist(String name, String info, String images, String location) {
        this.name = name;
        this.info = info;
        this.images = images;
        this.location = location;
    }
}
