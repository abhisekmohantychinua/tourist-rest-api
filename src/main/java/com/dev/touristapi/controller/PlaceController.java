package com.dev.touristapi.controller;

import com.dev.touristapi.entities.Place;
import com.dev.touristapi.services.PlaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/places/")
public class PlaceController {


    @Autowired
    private PlaceServices placeServices;


    //to get all the places
    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        return ResponseEntity.ok(this.placeServices.getAllPlaces());
    }


    //to get a place by is placeId
    @GetMapping("/{place_id}")
    public ResponseEntity<Optional<Place>> getSinglePlace(@PathVariable("place_id") String place_id) {
        return ResponseEntity.ok(this.placeServices.getSinglePlace(place_id));
    }


    //to add a place
    @PostMapping
    public ResponseEntity<Optional<Place>> addPlace(@RequestBody Place place) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.placeServices.addPlace(place));
    }

}
