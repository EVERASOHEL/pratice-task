package com.demosecurity.services;

import com.demosecurity.dto.applicationDTO.SalesDTO;
import com.demosecurity.model.applicationModels.Product;
import com.demosecurity.model.applicationModels.Sales;
import com.demosecurity.repository.applicationRepo.SalesRepository;
import com.demosecurity.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public ApiResponse createNewSales(SalesDTO salesDTO){
        try {
            Sales sales = salesRepository.save(new Sales(salesDTO));
            return ApiResponse.<Product>builder()
                    .data(sales)
                    .message("Sales Successfully Created!")
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Product>builder()
                    .message("Failed to insert sales: " + e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }
}
