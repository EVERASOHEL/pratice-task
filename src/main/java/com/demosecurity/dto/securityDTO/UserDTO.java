package com.demosecurity.dto.securityDTO;

import com.demosecurity.dto.securityDTO.RoleDTO;
import com.demosecurity.model.securityModels.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String userName;
    private Set<RoleDTO> roleDTO;

    public UserDTO(Object[] objects) {
        this.id = ((Number) objects[0]).longValue();
        this.email=(String) objects[1];
        this.userName=(String) objects[2];
        this.roleDTO = RoleDTO.fromRoles((String)objects[3]);
    }
}
