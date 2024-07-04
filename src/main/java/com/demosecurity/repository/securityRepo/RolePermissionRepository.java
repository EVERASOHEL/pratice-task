package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.permissionModels.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {

    @Query(nativeQuery = true,value = "select name from tbl_module_permission")
    List<String> findAllModuleName();
}
