package com.benwon.shop.repository;

import com.benwon.shop.entity.SaleOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SaleOrderDetailRepository extends JpaRepository<SaleOrderDetail, Long> {

    List<SaleOrderDetail> findByOrderIdOrderByCreatedByAsc(String orderId);
    
    List<SaleOrderDetail> findByOrderIdOrderByIdAsc(String orderId);
}
