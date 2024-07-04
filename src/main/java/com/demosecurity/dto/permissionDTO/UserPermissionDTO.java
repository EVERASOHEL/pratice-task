package com.demosecurity.dto.permissionDTO;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserPermissionDTO {

    private Long id;
    private Long userId;
    private String userName;
    private String RoleName;
    private List<String> selectedModules;

    public UserPermissionDTO(Long id, String userName, String role) {
        this.userId=id;
        this.userName=userName;
        this.RoleName=role;
    }
}
