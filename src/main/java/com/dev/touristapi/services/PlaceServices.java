package com.dev.touristapi.services;

import com.dev.touristapi.database.PlaceRepository;
import com.dev.touristapi.entities.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServices {


    @Autowired
    private PlaceRepository placeRepository;


    //used to provide List of place to controller
    public List<Place> getAllPlaces() {
        return this.placeRepository.findAll();
    }


    //used to provide a place to controller
    public Optional<Place> getSinglePlace(String place_id) {
        return this.placeRepository.findPlaceByPlaceId(place_id);
    }


    //used to add a place given by controller
    public Optional<Place> addPlace(Place place) {
        return Optional.of(this.placeRepository.insert(place));
    }
}
