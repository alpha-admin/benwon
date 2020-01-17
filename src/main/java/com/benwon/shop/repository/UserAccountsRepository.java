package com.benwon.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.benwon.shop.entity.UserAccounts;

@RepositoryRestResource
public interface UserAccountsRepository extends JpaRepository<UserAccounts, String> {
    UserAccounts findByUserName(String userName);
}
