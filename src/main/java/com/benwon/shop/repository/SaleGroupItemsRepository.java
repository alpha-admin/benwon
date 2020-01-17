package com.benwon.shop.repository;


import com.benwon.shop.entity.SaleGroupItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SaleGroupItemsRepository extends JpaRepository<SaleGroupItems, Long> {

    List<SaleGroupItems> findBySaleGroupId(@Param("saleGroupId") String saleGroupId);

    List<SaleGroupItems> findBySaleGroupIdOrderBySortingAsc(@Param("saleGroupId") String saleGroupId);
}
