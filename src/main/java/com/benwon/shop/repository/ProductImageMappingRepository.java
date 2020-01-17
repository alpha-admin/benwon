package com.benwon.shop.repository;

import com.benwon.shop.entity.ProductImageMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductImageMappingRepository extends JpaRepository<ProductImageMapping, Long> {

    List<ProductImageMapping> findByProductId(String productId);
}
