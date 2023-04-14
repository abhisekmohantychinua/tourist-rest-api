package com.dev.touristapi.database;

import com.dev.touristapi.entities.Tourist;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends MongoRepository<Tourist, ObjectId> {
}
