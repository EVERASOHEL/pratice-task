package com.demosecurity.repository.applicationRepo;

import com.demosecurity.model.applicationModels.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
}
