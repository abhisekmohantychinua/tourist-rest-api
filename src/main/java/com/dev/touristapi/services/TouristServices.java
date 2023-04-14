package com.dev.touristapi.services;

import com.dev.touristapi.database.PlaceRepository;
import com.dev.touristapi.database.TouristRepository;
import com.dev.touristapi.entities.Place;
import com.dev.touristapi.entities.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TouristServices {


    @Autowired
    private TouristRepository touristRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    //used to provide List of tourist to controller
    public List<Tourist> getTourist(String placeId) {
        try {
            return this.placeRepository.findPlaceByPlaceId(placeId).orElseThrow().getTouristIds();
        } catch (NoSuchElementException noSuchElementException) {
            return List.of();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }


    //used to add a tourist given by controller
    public Optional<Tourist> addTourist(Tourist tourist, String placeId) {
        try {
            Tourist tourist1 = this.touristRepository.insert(tourist);
            this.mongoTemplate.update(Place.class)
                    .matching(Criteria.where("placeId").is(placeId))
                    .apply(Update.update("touristIds", tourist1))
                    .first();
            return Optional.of(tourist1);
        } catch (NoSuchElementException noSuchElementException) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    //used to add a list of tourist given by controller
    public List<Tourist> addAllTourist(List<Tourist> tourists, String placeId) {
        try {
            List<Tourist> touristList = this.touristRepository.insert(tourists);
            touristList.forEach(tourist -> {
                this.mongoTemplate.update(Place.class)
                        .matching(Criteria.where("placeId").is(placeId))
                        .apply(new Update().push("touristIds").value(tourist.getId()))
                        .first();

            });
            return touristList;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
