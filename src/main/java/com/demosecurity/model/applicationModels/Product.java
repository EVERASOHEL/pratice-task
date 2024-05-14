package com.demosecurity.model.applicationModels;

import com.demosecurity.dto.applicationDTO.ProductDTO;
import com.demosecurity.model.securityModels.Role;
import com.demosecurity.model.securityModels.User;
import com.demosecurity.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tbl_product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName", unique = true, nullable = false)
    private String productName;

    @Column(name = "price")
    private Float price;

    @Column(name = "producthsncode", nullable = false)
    private String productHSNCode;

    public Product(ProductDTO productDTO) {
        this.id = Objects.nonNull(productDTO.getId()) ? productDTO.getId() : null;
        this.productName = productDTO.getProductName();
        this.price = productDTO.getPrice();
        this.productHSNCode = productDTO.getProductHSNCode();
    }
}
