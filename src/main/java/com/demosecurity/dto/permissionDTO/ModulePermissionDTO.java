package com.demosecurity.dto.permissionDTO;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ModulePermissionDTO {

    private Long id;
    private List<UserPermissionDTO> userPermissionDTO;
    private List<ModuleDTO> moduleDTO;
}
