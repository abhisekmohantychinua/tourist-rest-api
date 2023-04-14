package com.dev.touristapi.controller;

import com.dev.touristapi.entities.Review;
import com.dev.touristapi.services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/reviews/")
public class ReviewController {


    @Autowired
    private ReviewServices reviewServices;


    //to get all the reviews of the place
    @GetMapping("/list/{placeId}")
    public ResponseEntity<List<Review>> getAllReviewsOFAPlace(@PathVariable String placeId) {
        return ResponseEntity.ok(this.reviewServices.getAllReview(placeId));
    }

    //to add a review
    @PostMapping("/{placeId}")
    public ResponseEntity<Optional<Review>> addReviews(@RequestBody Review review,
                                                       @PathVariable String placeId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(this.reviewServices.addReview(review, placeId));
    }

}
