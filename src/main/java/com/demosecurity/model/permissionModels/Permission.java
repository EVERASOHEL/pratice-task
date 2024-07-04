package com.demosecurity.model.permissionModels;

import com.demosecurity.dto.permissionDTO.PermissionDTO;
import com.demosecurity.utils.Permissions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private ModulePermission module;

    @Column(name = "can_read", nullable = false)
    private boolean canRead;

    @Column(name = "can_write", nullable = false)
    private boolean canWrite;

    @Column(name = "can_update", nullable = false)
    private boolean canUpdate;

    @Column(name = "can_delete", nullable = false)
    private boolean canDelete;

    public Permission(List<PermissionDTO> permissionDTO, ModulePermission modulePermission) {
        this.module = modulePermission;

        for (PermissionDTO dto : permissionDTO) {
            String permissionName = dto.getPermissionName();
            boolean selected = dto.isSelected();

            switch (permissionName) {
                case "Read":
                    this.canRead = selected;
                    break;
                case "Write":
                    this.canWrite = selected;
                    break;
                case "Delete":
                    this.canDelete = selected;
                    break;
                case "Update":
                    this.canUpdate = selected;
                    break;
                default:
                    // Handle unknown permission name
                    break;
            }
        }
    }
}
