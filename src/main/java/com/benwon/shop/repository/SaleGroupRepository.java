package com.benwon.shop.repository;


import com.benwon.shop.entity.SaleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SaleGroupRepository extends JpaRepository<SaleGroup, String> {
}
