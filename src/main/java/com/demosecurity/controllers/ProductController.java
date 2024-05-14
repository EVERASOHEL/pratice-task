package com.demosecurity.controllers;

import com.demosecurity.dto.applicationDTO.ProductDTO;
import com.demosecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }
}
