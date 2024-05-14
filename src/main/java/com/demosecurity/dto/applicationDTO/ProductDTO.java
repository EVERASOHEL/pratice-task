package com.demosecurity.dto.applicationDTO;

import com.demosecurity.dto.securityDTO.UserDTO;
import com.demosecurity.model.applicationModels.Product;
import com.demosecurity.model.securityModels.User;
import com.demosecurity.utils.BaseDTO;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

    private Long id;
    private String productName;
    private Float price;
    private String productHSNCode;

    public ProductDTO(Product product) {
        this.id= Objects.nonNull(product.getId()) ? product.getId() : null;
        this.productName=product.getProductName();
        this.price=product.getPrice();
        this.productHSNCode=product.getProductHSNCode();
        this.setCreatedBy(Objects.nonNull(product.getCreatedBy()) ? product.getCreatedBy().getUsername() : null);
        this.setCreatedAt(product.getCreatedAt());
    }
}
