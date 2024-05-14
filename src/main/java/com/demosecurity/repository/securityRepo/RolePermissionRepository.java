package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.permissionModels.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {
}
