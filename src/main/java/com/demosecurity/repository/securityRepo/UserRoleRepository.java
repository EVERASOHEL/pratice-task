package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.permissionModels.UserPermission;
import com.demosecurity.model.securityModels.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Query(nativeQuery = true,value = "select * from tbl_user_role where user_id=:userId")
    UserRole findByUserId(@Param("userId") Long userId);

}
