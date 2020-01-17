package com.benwon.shop.service;

import com.benwon.shop.bo.FixProductItemStockQuantityEvent;
import com.benwon.shop.bo.ImageBo;
import com.benwon.shop.bo.ProductBo;
import com.benwon.shop.bo.ProductItemBo;
import com.benwon.shop.common.contract.SalesUnitEnum;
import com.benwon.shop.entity.Product;
import com.benwon.shop.entity.ProductImageMapping;
import com.benwon.shop.entity.ProductItem;
import com.benwon.shop.repository.ProductImageMappingRepository;
import com.benwon.shop.repository.ProductItemRepository;
import com.benwon.shop.repository.ProductRepository;
import com.benwon.shop.vo.SalesUnitVo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xyz.downgoon.snowflake.Snowflake;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final Snowflake snowflake;
    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductImageMappingRepository productImageMappingRepository;

    @Transactional(rollbackFor = Exception.class)
    public Product create(ProductBo productBo) {
        ModelMapper modelMapper = new ModelMapper();
        String productId = String.valueOf(snowflake.nextId());

        Product product = modelMapper.map(productBo, Product.class);
        product.setId(productId);
        // 2019-07-31
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        product.setExpiryDate(LocalDate.parse(productBo.getExpiryDate(), formatter));

        product.setCreatedBy("");
        product.setCreatedDate(LocalDateTime.now());
        product.setLastModifiedBy("");
        product.setLastModifiedDate(LocalDateTime.now());
        productRepository.save(product);

        List<ProductItemBo> productItemList = productBo.getProductItemList();
        for (int i = 0; i < productItemList.size(); i++) {
            ProductItemBo productItemBo = productItemList.get(i);
            ProductItem productItem = modelMapper.map(productItemBo, ProductItem.class);
            productItem.setId(String.valueOf(snowflake.nextId()));
            productItem.setProductId(productId);
            productItem.setSorting(i);
            productItem.setCreatedBy("");
            productItem.setCreatedDate(LocalDateTime.now());
            productItem.setLastModifiedBy("");
            productItem.setLastModifiedDate(LocalDateTime.now());
            productItem = productItemRepository.save(productItem);
        }
        //
        List<ImageBo> imageList = productBo.getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            ImageBo imageBo = imageList.get(i);
            ProductImageMapping productImageMapping = new ProductImageMapping();
            productImageMapping.setProductId(productId);
            productImageMapping.setImageId(imageBo.getId());
            productImageMapping.setSorting(i);
            productImageMapping.setResourceUrl(imageBo.getResourceUrl());
            productImageMapping.setCreatedBy("");
            productImageMapping.setCreatedDate(LocalDateTime.now());
            productImageMapping.setLastModifiedBy("");
            productImageMapping.setLastModifiedDate(LocalDateTime.now());
            productImageMappingRepository.save(productImageMapping);
        }
        return product;
    }

    public ProductBo getDetail(String id) {
        ModelMapper modelMapper = new ModelMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Product product = productRepository.getOne(id);
        ProductBo productBo = modelMapper.map(product, ProductBo.class);
        productBo.setExpiryDate(formatter.format(product.getExpiryDate()));

        List<ImageBo> imageList = productImageMappingRepository.findByProductId(id).stream()
                .map(productImageMapping -> new ImageBo(productImageMapping.getId(), productImageMapping.getResourceUrl()))
                .collect(Collectors.toList());
        productBo.setImageList(imageList);

        List<ProductItem> productItemList = productItemRepository.findByProductIdOrderBySortingAsc(id);
        Type listType = new TypeToken<List<ProductItemBo>>() {
        }.getType();
        List<ProductItemBo> productItemBoList = modelMapper.map(productItemList, listType);
        List<SalesUnitVo> salesUnitList = new ArrayList<>();
        salesUnitList.add(new SalesUnitVo(SalesUnitEnum.BASIC.getCode(), product.getBasicUnitDesc()));
        //salesUnitList.add(new SalesUnitVo(SalesUnitEnum.SET.getCode(), product.getSetUnitDesc()));
        productItemBoList.forEach(productItemBo -> {
            productItemBo.setProductImgResourceUrl(product.getProductImgResourceUrl());
            productItemBo.setSalesUnitList(salesUnitList);
            productItemBo.setBuyCount(0);
        });
        productBo.setProductItemList(productItemBoList);

        Integer stockQuantitySum = productItemRepository.findByProductIdStockQuantitySum(id);
        productBo.setStockQuantitySum(stockQuantitySum);
        return productBo;
    }

    @Transactional(rollbackFor = Exception.class)
    public Product update(String productID, ProductBo productBo) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = productRepository.getOne(productID);
        product.setProductName(productBo.getProductName());
        product.setProductImgId(productBo.getProductImgId());
        product.setProductImgResourceUrl(productBo.getProductImgResourceUrl());
        product.setSalePrice(productBo.getSalePrice());
        product.setDiscPrice(productBo.getDiscPrice());
        // product.setSaleUnitDesc(productBo.getSaleUnitDesc());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        product.setExpiryDate(LocalDate.parse(productBo.getExpiryDate(), formatter));// 2019-07-31
        product.setBasicUnitDesc(productBo.getBasicUnitDesc());
        // product.setSetUnitDesc(productBo.getSetUnitDesc());
        product.setNorm(productBo.getNorm());
        product.setProductIntroduction(productBo.getProductIntroduction());
        product.setIntroduction(productBo.getIntroduction());
        product.setSpecifications(productBo.getSpecifications());
        product.setLastModifiedBy("");
        product.setLastModifiedDate(LocalDateTime.now());
        productRepository.save(product);
        //
        List<String> productItemIDOldList = productItemRepository.findByProductIdOrderBySortingAsc(productID).stream().map(productItem -> productItem.getId()).collect(Collectors.toList());
        List<String> productItemIDList = productBo.getProductItemList().stream().map(productItemBo -> productItemBo.getId()).collect(Collectors.toList());
        productItemIDOldList.removeIf(productItemID -> productItemIDList.contains(productItemID));
        productItemIDOldList.forEach(productItemID -> {
            ProductItem productItem = productItemRepository.getOne(productItemID);
            productItemRepository.delete(productItem);
        });
        List<ProductItemBo> productItemList = productBo.getProductItemList();
        for (int i = 0; i < productItemList.size(); i++) {
            ProductItemBo productItemBo = productItemList.get(i);
            ProductItem productItem = null;
            if (StringUtils.hasText(productItemBo.getId())) {
                productItem = productItemRepository.getOne(productItemBo.getId());
                productItem.setProductItemName(productItemBo.getProductItemName());
                productItem.setStockQuantity(productItemBo.getStockQuantity());
            } else {
                productItem = modelMapper.map(productItemBo, ProductItem.class);
                productItem.setId(String.valueOf(snowflake.nextId()));
                productItem.setProductId(product.getId());
            }
            productItem.setSorting(i);
            productItem.setCreatedBy("");
            productItem.setCreatedDate(LocalDateTime.now());
            productItem.setLastModifiedBy("");
            productItem.setLastModifiedDate(LocalDateTime.now());
            productItem = productItemRepository.save(productItem);
        }
        productImageMappingRepository.findByProductId(productID)
                .forEach(productImageMapping -> productImageMappingRepository.delete(productImageMapping));
        //
        List<ImageBo> imageList = productBo.getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            ImageBo imageBo = imageList.get(i);
            ProductImageMapping productImageMapping = new ProductImageMapping();
            productImageMapping.setProductId(productID);
            productImageMapping.setImageId(imageBo.getId());
            productImageMapping.setSorting(i);
            productImageMapping.setResourceUrl(imageBo.getResourceUrl());
            productImageMapping.setCreatedBy("");
            productImageMapping.setCreatedDate(LocalDateTime.now());
            productImageMapping.setLastModifiedBy("");
            productImageMapping.setLastModifiedDate(LocalDateTime.now());
            productImageMappingRepository.save(productImageMapping);
        }
        return product;
    }
    
    
    public void fixProductItemStockQuantity(String productItemId, FixProductItemStockQuantityEvent fixProductItemStockQuantityEvent) {
    	ProductItem productItem = productItemRepository.getOne(productItemId);
    	productItem.setProductItemName(fixProductItemStockQuantityEvent.getProductItemName());
    	productItem.setStockQuantity(fixProductItemStockQuantityEvent.getStockQuantity());
    	productItemRepository.save(productItem);
    }
}
