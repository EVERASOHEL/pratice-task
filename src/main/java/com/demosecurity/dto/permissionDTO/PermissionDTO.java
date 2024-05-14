package com.demosecurity.dto.permissionDTO;

import lombok.*;

@Builder
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PermissionDTO {
    private Long id;
    private String permissionName;
    private boolean selected;
}
