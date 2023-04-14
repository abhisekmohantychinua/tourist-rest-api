package com.dev.touristapi.database;

import com.dev.touristapi.entities.Place;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends MongoRepository<Place, ObjectId> {
    //to get Place object by its place id (JPEL)
    public Optional<Place> findPlaceByPlaceId(String placeId);
}
