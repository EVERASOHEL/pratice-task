package com.demosecurity.dto.permissionDTO;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
public class UserModulePermissionsDTO {
    private List<UserPermissionDTO> userPermissionDTOList;
    private List<ModuleDTO> moduleDTOList;

    public UserModulePermissionsDTO(List<UserPermissionDTO> userPermissionDTOList, List<ModuleDTO> moduleDTOList) {
        this.userPermissionDTOList = userPermissionDTOList;
        this.moduleDTOList = moduleDTOList;
    }
}
