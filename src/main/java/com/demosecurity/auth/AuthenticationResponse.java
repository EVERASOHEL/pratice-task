package com.demosecurity.auth;

import com.demosecurity.dto.permissionDTO.ModulePermissionDTO;
import com.demosecurity.dto.permissionDTO.UserRoleModulePermissionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String message;
    private Map<String, Object> modulePermissions;
    private int statusCode;

}
