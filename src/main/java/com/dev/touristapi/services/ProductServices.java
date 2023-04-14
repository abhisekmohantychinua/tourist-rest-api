package com.dev.touristapi.services;

import com.dev.touristapi.database.PlaceRepository;
import com.dev.touristapi.database.ProductRepository;
import com.dev.touristapi.entities.Place;
import com.dev.touristapi.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {


    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    //used to provide List of Product to controller
    public List<Product> getProducts(String placeId) {
        try {
            Place place = this.placeRepository.findPlaceByPlaceId(placeId).get();
            return place.getProductIds();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

    }


    //used add a product given by controller
    public Optional<Product> addProduct(Product product, String placeId) {
        try {
            Product product1 = this.productRepository.insert(product);
            this.mongoTemplate.update(Place.class)
                    .matching(Criteria.where("placeId").is(placeId))
                    .apply(Update.update("productIds", product1))
                    .first();

            return Optional.of(product1);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    //used to add a list of product given by controller
    public List<Product> addAllProduct(List<Product> products, String placeId) {
        try {
            List<Product> products1 = this.productRepository.insert(products);
            products1.forEach(product -> {
                this.mongoTemplate.update(Place.class)
                        .matching(Criteria.where("placeId").is(placeId))
                        .apply(new Update().push("productIds").value(product.getId()))
                        .first();
            });
            return products1;
        } catch (Exception e) {
            return List.of();
        }
    }
}
