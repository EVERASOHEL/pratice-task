package com.demosecurity.dto.securityDTO;

import com.demosecurity.model.securityModels.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String name;

    public RoleDTO(Role role) {
        this.name=role.getName();
    }

    public RoleDTO(String name) {
        this.name=name;
    }

    public RoleDTO(Object[] roles) {
        this.id= (Long)roles[0];
        this.name=(String)roles[1];
    }

    public static Set<RoleDTO> fromRoles(String rolenames) {
        Set<RoleDTO> roleDTOS=new HashSet<>();
        roleDTOS.add(new RoleDTO(rolenames));
        return roleDTOS;
    }
}
