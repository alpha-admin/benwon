package com.benwon.shop.repository;

import com.benwon.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, String> {

    // 依產品名搜尋
    @Query("select p from Product p where p.productName like %:productName%")
    List<Product> findByProductNameLike(@Param("productName") String productName);

    // 搜尋入口網站銷售的商品
    @Query("select p from Product p, SalePortal sp where p.id =  sp.productId order by sp.sorting ")
    List<Product> getSaleOnPortal();

    @Query("select p from Product p, SalePortal sp where p.id =  sp.productId order by sp.sorting")
    Page<Product> findSaleOnPortal(Pageable pageable);

    // Prevents GET /books/:id
    @Override
    Product getOne(String id);

    // Prevents GET /books
    @Override
    Page<Product> findAll(Pageable pageable);

    // Prevents POST /books and PATCH /books/:id
    @Override
    Product save(Product s);

    // Prevents DELETE /books/:id
    @Override
    @RestResource(exported = false)
    void delete(Product t);
}
