package com.demosecurity.services;

import com.demosecurity.dto.applicationDTO.ProductDTO;
import com.demosecurity.utils.ApiResponse;
import com.demosecurity.model.applicationModels.Product;
import com.demosecurity.repository.applicationRepo.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ApiResponse createProduct(ProductDTO productDTO){
        try {
            Product product = productRepository.save(new Product(productDTO));
            return ApiResponse.<Product>builder()
                    .data(product)
                    .message("Product Successfully Inserted!")
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return ApiResponse.<Product>builder()
                    .message("Failed to insert product: " + e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    public ApiResponse findAllProduct(){
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOList = products.stream().map(ProductDTO::new).collect(Collectors.toList());
            return ApiResponse.builder()
                    .message("success")
                    .data(productDTOList)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }catch (Exception e) {
            return ApiResponse.<Product>builder()
                    .message("Failed to fetch products: " + e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }
}
