package com.benwon.shop.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.benwon.shop.bo.FixProductItemStockQuantityEvent;
import com.benwon.shop.bo.ProductBo;
import com.benwon.shop.entity.Product;
import com.benwon.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class ProductRestController {
    private final ProductService productService;
    
    /**
     * 建立商品
     * @param productBo
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/v1/product")
    public Product create(@RequestBody ProductBo productBo) {
        log.debug("ProductBo=" + productBo);
        return productService.create(productBo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/v1/product/{id}")
    public ProductBo detail(@PathVariable(name = "id") String id) {
        ProductBo productBo = productService.getDetail(id);
        log.debug("ProductBo=" + productBo);
        return productBo;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/v1/product/{id}")
    public Product update(@PathVariable(name = "id") String id,
                          @RequestBody ProductBo productBo) {
        log.debug("ProductBo=" + productBo);
        Product product = productService.update(id, productBo);
        return product;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/v1/product/{productId}/productItem/{productItemId}/fixInfoEvent")
	public void fixProductItemStockQuantity(
    		@PathVariable(name = "productId") String productId,
    		@PathVariable(name = "productItemId") String productItemId,
                          @RequestBody FixProductItemStockQuantityEvent fixProductItemStockQuantityEvent) {
        productService.fixProductItemStockQuantity(productItemId, fixProductItemStockQuantityEvent);
    }
}
