package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.securityModels.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String roleName);

    @Query(nativeQuery = true,value = "select * from tbl_role")
    List<Object[]> getAllRoles();

}
