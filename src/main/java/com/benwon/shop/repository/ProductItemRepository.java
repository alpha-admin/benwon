package com.benwon.shop.repository;

import com.benwon.shop.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem, String> {

    // 加總商品數量
    @Query("SELECT SUM(pi.stockQuantity) FROM ProductItem pi WHERE pi.productId = :productId")
    Integer findByProductIdStockQuantitySum(@Param("productId") String productId);

    List<ProductItem> findByProductIdOrderBySortingAsc(@Param("productId") String productId);

}
