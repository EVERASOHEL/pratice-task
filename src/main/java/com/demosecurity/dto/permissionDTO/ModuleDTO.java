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

    public ModuleDTO(String moduleName, List<PermissionDTO> list) {
        this.moduleName=moduleName;
        this.permissionDTO=list;
    }
}
