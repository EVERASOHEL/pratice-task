package com.demosecurity.dto.permissionDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserRoleModulePermissionDTO {

    private Long id;
    private String moduleName;
    private Boolean canWrite;
    private Boolean canRead;
    private Boolean canUpdate;
    private Boolean canDelete;

    public UserRoleModulePermissionDTO(Object[] objects) {
        this.id = ((Number) objects[0]).longValue();
        this.moduleName = (String) objects[1];
        this.canWrite = (Boolean) objects[2];
        this.canRead = (Boolean) objects[3];
        this.canUpdate = (Boolean) objects[4];
        this.canDelete = (Boolean) objects[5];
    }
}
