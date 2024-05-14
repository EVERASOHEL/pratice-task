package com.demosecurity.dto.applicationDTO;

import lombok.*;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SalesDTO {

    private Long id;
    private String companyName;
    private String gstType;
    private ProductDTO productDTO;
}
