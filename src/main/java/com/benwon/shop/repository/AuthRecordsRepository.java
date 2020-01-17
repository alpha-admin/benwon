package com.benwon.shop.repository;

import com.benwon.shop.entity.AuthRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AuthRecordsRepository extends JpaRepository<AuthRecords, String> {

    AuthRecords findByAuthCode(String authCode);
}
