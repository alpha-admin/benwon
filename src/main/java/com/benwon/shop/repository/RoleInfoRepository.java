package com.benwon.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.benwon.shop.entity.RoleInfo;

@RepositoryRestResource
public interface RoleInfoRepository extends JpaRepository<RoleInfo, Long> {

    @Query("SELECT r FROM RoleInfo r, UserRoleMapping urm WHERE r.id = urm.roleId and urm.userId = :userId")
    List<RoleInfo> findByUserId(@Param("userId") String userId);
}
