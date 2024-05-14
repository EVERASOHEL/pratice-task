package com.demosecurity.services;

import com.demosecurity.dto.permissionDTO.ModuleDTO;
import com.demosecurity.dto.permissionDTO.ModulePermissionDTO;
import com.demosecurity.dto.permissionDTO.UserPermissionDTO;
import com.demosecurity.model.permissionModels.ModulePermission;
import com.demosecurity.model.permissionModels.Permission;
import com.demosecurity.model.permissionModels.RolePermission;
import com.demosecurity.model.permissionModels.UserPermission;
import com.demosecurity.model.securityModels.Role;
import com.demosecurity.model.securityModels.User;
import com.demosecurity.model.securityModels.UserRole;
import com.demosecurity.repository.securityRepo.*;
import com.demosecurity.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RolePermissionService {

    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;
    private final ModulePermissionRepository modulePermissionRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public RolePermissionService(UserRepository userRepository,
                                 RolePermissionRepository rolePermissionRepository,
                                 PermissionRepository permissionRepository,
                                 ModulePermissionRepository modulePermissionRepository,
                                 UserPermissionRepository userPermissionRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionRepository = permissionRepository;
        this.modulePermissionRepository = modulePermissionRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public ApiResponse ViewAllPermission() {
        List<RolePermission> permissionList = rolePermissionRepository.findAll();
        if (!permissionList.isEmpty()) {

        }
        return ApiResponse.builder()
                .data(permissionList)
                .build();
    }

    public ApiResponse saveNewPermission(ModulePermissionDTO modulePermissionDTO) {

        for (UserPermissionDTO userPermissionDTO : modulePermissionDTO.getUserPermissionDTO()) {
            for (String selectedModuleName : userPermissionDTO.getSelectedModules()) {
                for (ModuleDTO moduleDTO : modulePermissionDTO.getModuleDTO()) {
                    if (selectedModuleName.equalsIgnoreCase(moduleDTO.getModuleName())) {
                        ModulePermission modulePermissionName = modulePermissionRepository.findModulePermissionByName(moduleDTO.getModuleName());
                        if (Objects.isNull(modulePermissionName)) {
                            return ApiResponse.builder().message("Module name is not found!").statusCode(HttpStatus.NOT_FOUND.value()).build();
                        }
                        // Save permission
                        Permission permission = permissionRepository.save(new Permission(moduleDTO.getPermissionDTO(), modulePermissionName));

                        UserRole userRole = userRoleRepository.findByUserId(userPermissionDTO.getUserId());
                        Role role = roleRepository.findById(userRole.getRole().getId())
                                .orElseThrow(() -> new RuntimeException("Role not found"));
                        // Save role permission with the correct parameters
                        final RolePermission rolePermission = rolePermissionRepository.save(new RolePermission(role, permission));

                        final User user = userRepository.findById(userPermissionDTO.getUserId())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                        userPermissionRepository.save(new UserPermission(user, rolePermission));
                    }
                }
            }
        }
        return ApiResponse.builder().message("Permission is successfully created.").statusCode(HttpStatus.OK.value()).build();
    }

}
