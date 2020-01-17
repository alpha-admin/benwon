package com.benwon.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.benwon.shop.entity.UserLine;

@RepositoryRestResource
public interface UserLineRepository extends JpaRepository<UserLine, Long> {

    UserLine findByLineId(@Param("lineId") String lineId);
    UserLine findByUserId(@Param("userId") String userId);
}
