package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.securityModels.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String roleName);

}
