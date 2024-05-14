package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.permissionModels.ModulePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulePermissionRepository extends JpaRepository<ModulePermission,Long> {

    ModulePermission findModulePermissionByName(String modualName);

}
