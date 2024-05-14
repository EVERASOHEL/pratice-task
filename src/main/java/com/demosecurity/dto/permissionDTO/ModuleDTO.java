package com.demosecurity.dto.permissionDTO;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ModuleDTO {

    private Long id;
    private String moduleName;
    private String description;
    private List<PermissionDTO> permissionDTO;

}
