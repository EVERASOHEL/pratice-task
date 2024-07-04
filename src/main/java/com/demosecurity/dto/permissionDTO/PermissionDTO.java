package com.demosecurity.dto.permissionDTO;

import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PermissionDTO {
    private Long id;
    private String permissionName;
    private boolean selected;

    public PermissionDTO(String permissionName, boolean selected) {
        this.permissionName=Objects.nonNull(permissionName) ? permissionName : null;
        this.selected= Objects.nonNull(selected) ? selected : false;
    }
}
