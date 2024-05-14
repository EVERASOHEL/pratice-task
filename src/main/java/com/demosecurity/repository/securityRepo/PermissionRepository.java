package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.permissionModels.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
