package com.dev.touristapi.services;

import com.dev.touristapi.database.PlaceRepository;
import com.dev.touristapi.database.ReviewRepository;
import com.dev.touristapi.entities.Place;
import com.dev.touristapi.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServices {


    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    //used to provide List of review to controller
    public List<Review> getAllReview(String placeId) {
        try {
            Place place = this.placeRepository.findPlaceByPlaceId(placeId).get();
            return place.getReviewIds();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //used to add a single review given by controller
    public Optional<Review> addReview(Review review, String placeId) {
        try {
            Review review1 = this.reviewRepository.insert(review);
            this.mongoTemplate.update(Place.class)
                    .matching(Criteria.where("placeId").is(placeId))
                    .apply(new Update().push("reviewIds").value(review1.getId()))
                    .first();
            return Optional.of(review1);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
