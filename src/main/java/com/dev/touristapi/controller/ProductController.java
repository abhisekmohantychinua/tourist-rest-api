package com.dev.touristapi.controller;

import com.dev.touristapi.entities.Product;
import com.dev.touristapi.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/products/")
public class ProductController {


    @Autowired
    private ProductServices productServices;


    //to get all the products of the place
    @GetMapping("/{placeId}")
    public ResponseEntity<List<Product>> getAllProduct(@PathVariable String placeId) {
        return ResponseEntity.ok(this.productServices.getProducts(placeId));
    }


    //to add a product to the place
    @PostMapping("/single/{placeId}")
    public ResponseEntity<Optional<Product>> addProduct(@RequestBody Product product, @PathVariable String placeId) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productServices.addProduct(product, placeId));
    }


    //to add a list of products to the place
    @PostMapping("/list/{placeId}")
    public ResponseEntity<List<Product>> addAllProduct(@RequestBody List<Product> products,
                                                       @PathVariable String placeId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(this.productServices.addAllProduct(products, placeId));
    }
}
