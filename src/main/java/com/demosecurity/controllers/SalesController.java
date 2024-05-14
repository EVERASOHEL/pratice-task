package com.demosecurity.controllers;

import com.demosecurity.dto.applicationDTO.SalesDTO;
import com.demosecurity.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/newSale")
    public ResponseEntity<?> createNewProduct(@RequestBody SalesDTO salesDTO){
        return ResponseEntity.ok(salesService.createNewSales(salesDTO));
    }

}
