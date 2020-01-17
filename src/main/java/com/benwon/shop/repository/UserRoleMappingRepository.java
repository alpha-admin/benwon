package com.benwon.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.benwon.shop.entity.UserRoleMapping;

@RepositoryRestResource(exported = false)
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Long> {

    List<UserRoleMapping> findByUserId(String userId);
}
