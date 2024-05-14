package com.demosecurity.model.permissionModels;

import com.demosecurity.dto.permissionDTO.UserPermissionDTO;
import com.demosecurity.model.securityModels.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tbl_user_permission")
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "role_permission_id", referencedColumnName = "id"),
            @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")
    })
    private RolePermission rolePermission;

    public UserPermission(UserPermissionDTO userPermissionDTO) {
    }

    public UserPermission(User user, RolePermission rolePermission) {
        this.user=user;
        this.rolePermission=rolePermission;
    }

    public static List<UserPermission> fromDTOtListTOModelList(List<UserPermissionDTO> userPermissionDTOList, AtomicReference<Permission> permission){
        return userPermissionDTOList.stream().map(UserPermission::new).collect(Collectors.toList());
    }
}
