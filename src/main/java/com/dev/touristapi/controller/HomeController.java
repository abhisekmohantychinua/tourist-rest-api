package com.dev.touristapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("""
                Read the documentation and access api.
                documentation : https://documenter.getpostman.com/view/23395461/2s93Xx1Q6i""");
    }

}
