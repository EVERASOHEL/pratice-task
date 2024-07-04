package com.demosecurity.services;

import com.demosecurity.dto.permissionDTO.*;
import com.demosecurity.dto.securityDTO.RoleDTO;
import com.demosecurity.model.permissionModels.ModulePermission;
import com.demosecurity.model.permissionModels.Permission;
import com.demosecurity.model.permissionModels.RolePermission;
import com.demosecurity.model.permissionModels.UserPermission;
import com.demosecurity.model.securityModels.Role;
import com.demosecurity.model.securityModels.User;
import com.demosecurity.model.securityModels.UserRole;
import com.demosecurity.repository.securityRepo.*;
import com.demosecurity.utils.ApiResponse;
import com.demosecurity.utils.Permissions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RolePermissionService {

    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;
    private final ModulePermissionRepository modulePermissionRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
        List<String> permissionList = rolePermissionRepository.findAllModuleName();
        if (!permissionList.isEmpty()) {
            return ApiResponse.builder()
                    .message("data successfully retrived")
                    .data(permissionList)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }
        return ApiResponse.builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("data not found!")
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

    @Transactional
    public ApiResponse getAllPermissionList() {

        String userNameQuery = "SELECT " +
                "    tu.id, " +
                "    tu.username, " +
                "    tr.name " +
                "FROM " +
                "    tbl_user_role tur " +
                "LEFT JOIN " +
                "    tbl_user tu ON tu.id = tur.user_id " +
                "LEFT JOIN " +
                "    tbl_role tr ON tur.role_id = tr.id";

        final Query userNameQueryString = entityManager.createNativeQuery(userNameQuery);

        List<Object[]> getAllUser = userNameQueryString.getResultList();

        List<UserModulePermissionsDTO> userModulePermissionsDTOList = new ArrayList<>();
        for (Object object[] : getAllUser) {

            Long id = (Long) object[0];
            String userName = (String) object[1];
            String role = (String) object[2];

            List<UserPermissionDTO> userPermissionDTOList = new ArrayList<>();
            userPermissionDTOList.add(new UserPermissionDTO(id, userName, role));

            String permissionNameQuery = "" +
                    "SELECT " +
                    "    tmp.name, " +
                    "    tp.can_read, " +
                    "    tp.can_write, " +
                    "    tp.can_update, " +
                    "    tp.can_delete " +
                    "FROM " +
                    "    tbl_user_permission tup " +
                    "LEFT JOIN " +
                    "    tbl_permission tp ON tup.permission_id = tp.id " +
                    "LEFT JOIN " +
                    "    tbl_module_permission tmp ON tp.module_id = tmp.id " +
                    "WHERE " +
                    "    tup.user_id = :userId";

            final Query query = entityManager.createNativeQuery(permissionNameQuery);
            query.setParameter("userId", Objects.nonNull(id) ? id : null);
            List<Object[]> allPermissionsModuleWise = query.getResultList();

            List<ModuleDTO> moduleDTOList = new ArrayList<>();
            for (Object[] permissions : allPermissionsModuleWise) {

                String moduleName = (String) permissions[0];

                List<PermissionDTO> permissionDTOList = new ArrayList<>();
                permissionDTOList.add(new PermissionDTO(Permissions.READ.getValue(), (Boolean) permissions[1]));
                permissionDTOList.add(new PermissionDTO(Permissions.WRITE.getValue(), (Boolean) permissions[2]));
                permissionDTOList.add(new PermissionDTO(Permissions.UPDATE.getValue(), (Boolean) permissions[3]));
                permissionDTOList.add(new PermissionDTO(Permissions.DELETE.getValue(), (Boolean) permissions[4]));

                moduleDTOList.add(new ModuleDTO(moduleName, permissionDTOList));

            }
            userModulePermissionsDTOList.add(new UserModulePermissionsDTO(userPermissionDTOList, moduleDTOList));
        }
        if (userModulePermissionsDTOList.size() > 0) {
            return ApiResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .data(userModulePermissionsDTOList)
                    .message("Data Successfully retrieved.")
                    .build();
        }
        return ApiResponse.builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .data(null)
                .message("No Data Found")
                .build();
    }

    public ApiResponse findAllRoles() {
        final List<Object[]> allRoles = roleRepository.getAllRoles();
        if (!allRoles.isEmpty()) {
            final List<RoleDTO> dtoList = allRoles.stream().map(RoleDTO::new).collect(Collectors.toList());
            if (dtoList.size() > 0) {
                return ApiResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(dtoList)
                        .message("data successfully received")
                        .build();
            }
        }
        return ApiResponse.builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .data(null)
                .message("data not received")
                .build();
    }
}
