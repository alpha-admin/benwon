package com.benwon.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.benwon.shop.entity.SalePortal;

@RepositoryRestResource(exported = true)
public interface SalePortalRepository extends JpaRepository<SalePortal, String>{

}
