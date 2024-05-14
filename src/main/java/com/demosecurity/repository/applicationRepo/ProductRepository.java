package com.demosecurity.repository.applicationRepo;

import com.demosecurity.model.applicationModels.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Boolean existsByProductName(String productName);

}
