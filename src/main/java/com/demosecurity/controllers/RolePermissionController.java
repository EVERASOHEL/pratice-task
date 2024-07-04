package com.demosecurity.controllers;

import com.demosecurity.dto.permissionDTO.ModulePermissionDTO;
import com.demosecurity.services.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rolePermission")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping("/getAllPermissionList")
    public ResponseEntity<?> getAllRolePermissionList(){
        return ResponseEntity.ok(rolePermissionService.ViewAllPermission());
    }

    @PostMapping("/createNewPermission")
    public ResponseEntity<?> createNewPermission(@RequestBody ModulePermissionDTO modulePermissionDTO){
        return ResponseEntity.ok(rolePermissionService.saveNewPermission(modulePermissionDTO));
    }

    @GetMapping("/getAllAccessControl")
    public ResponseEntity<?> getAllUserModulePermissions(){
        return  ResponseEntity.ok(rolePermissionService.getAllPermissionList());
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<?> getAllRoles(){
        return  ResponseEntity.ok(rolePermissionService.findAllRoles());
    }

}
