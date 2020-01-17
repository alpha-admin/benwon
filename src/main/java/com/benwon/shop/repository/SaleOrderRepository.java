package com.benwon.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.benwon.shop.entity.SaleOrder;

@RepositoryRestResource
public interface SaleOrderRepository extends JpaRepository<SaleOrder, String> {

	List<SaleOrder> findByPurchaserUserIdOrderByCreatedDateDesc(@Param("purchaserUserId") String purchaserUserId);

	Page<SaleOrder> findByIsShipped(@Param("isShipped") Integer isShipped, Pageable pageable);
}
