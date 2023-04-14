package com.dev.touristapi.controller;

import com.dev.touristapi.entities.Tourist;
import com.dev.touristapi.services.TouristServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/tourist/")
public class TouristController {


    @Autowired
    private TouristServices touristServices;


    //to get all tourist of that Place
    @GetMapping("/{placeId}")
    public ResponseEntity<List<Tourist>> getTourist(@PathVariable String placeId) {
        return ResponseEntity.ok(this.touristServices.getTourist(placeId));
    }


    //to add a single tourist place
    @PostMapping("/single/{placeId}")
    public ResponseEntity<Optional<Tourist>> addTourist(@RequestBody Tourist tourist
            , @PathVariable String placeId) {
        System.out.println(tourist);
        return ResponseEntity.accepted().body(this.touristServices.addTourist(tourist, placeId));
    }


    //to add a list of tourist place
    @PostMapping("/list/{placeId}")
    public ResponseEntity<List<Tourist>> addAllTourist(@RequestBody List<Tourist> tourists,
                                                       @PathVariable String placeId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(this.touristServices.addAllTourist(tourists, placeId));
    }
}
