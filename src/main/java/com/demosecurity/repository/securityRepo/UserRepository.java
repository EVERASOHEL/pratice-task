package com.demosecurity.repository.securityRepo;

import com.demosecurity.model.securityModels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(@Param("userName") String userName);

    @Query(nativeQuery = true,value = "select tu.id,tu.email,tu.username,tr.name from tbl_user tu left join tbl_user_role tur on tu.id=tur.user_id left join tbl_role tr on tur.role_id=tr.id")
    List<Object[]> findAllRegisterUsers();

}
