package com.demosecurity.model.applicationModels;

import com.demosecurity.dto.applicationDTO.SalesDTO;
import com.demosecurity.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tbl_sales")
public class Sales extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "companyname",nullable = false)
    private String companyName;

    @Column(name = "gsttype")
    private String gstType;

//    @Column(name = "product_id")
//    private Product product;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;

    public Sales(SalesDTO salesDTO) {
        this.id= Objects.nonNull(salesDTO.getId()) ? salesDTO.getId() : null;
        this.companyName=salesDTO.getCompanyName();
        this.gstType=salesDTO.getGstType();
//        this.product=new Product(salesDTO.getProductDTO());
    }
}
