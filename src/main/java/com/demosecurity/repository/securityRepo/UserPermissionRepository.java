package com.demosecurity.repository.securityRepo;

import com.demosecurity.dto.permissionDTO.UserRoleModulePermissionDTO;
import com.demosecurity.model.permissionModels.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission,Long> {

}


