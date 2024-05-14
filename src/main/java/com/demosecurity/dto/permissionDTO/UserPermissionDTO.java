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
    private List<String> selectedModules;

}
