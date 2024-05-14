package com.demosecurity.dto.securityDTO;

import com.demosecurity.dto.securityDTO.RoleDTO;
import com.demosecurity.model.securityModels.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    private String userName;
    private Set<RoleDTO> roleDTO;
}
