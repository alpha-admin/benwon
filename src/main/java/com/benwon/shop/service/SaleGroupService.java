package com.benwon.shop.service;

import com.benwon.shop.bo.SaleGroupBo;
import com.benwon.shop.bo.SaleGroupItemBo;
import com.benwon.shop.entity.Product;
import com.benwon.shop.entity.SaleGroup;
import com.benwon.shop.entity.SaleGroupItems;
import com.benwon.shop.repository.ProductItemRepository;
import com.benwon.shop.repository.ProductRepository;
import com.benwon.shop.repository.SaleGroupItemsRepository;
import com.benwon.shop.repository.SaleGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.downgoon.snowflake.Snowflake;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleGroupService {
    private final Snowflake snowflake;
    private final SaleGroupRepository saleGroupRepository;
    private final SaleGroupItemsRepository saleGroupItemsRepository;
    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;
    
    private String detaultGroupImgResourceUrl = "/images/banner/banner_test_1.png";

    @Transactional(rollbackFor = Exception.class)
    public SaleGroup create(SaleGroupBo saleGroupBo) {
        log.debug("saleGroupBo=" + saleGroupBo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String saleGroupID = String.valueOf(snowflake.nextId());
        SaleGroup saleGroup = new SaleGroup();
        saleGroup.setId(saleGroupID);
        saleGroup.setGroupName(saleGroupBo.getGroupName());
        saleGroup.setGroupImgId(saleGroupBo.getGroupImgId());
        saleGroup.setGroupImgResourceUrl(saleGroupBo.getGroupImgResourceUrl());
        saleGroup.setSaleStartDate(LocalDateTime.parse(saleGroupBo.getSaleStartDate(), formatter));
        saleGroup.setSaleEndDate(LocalDateTime.parse(saleGroupBo.getSaleEndDate(), formatter));
        saleGroup.setSaleStatus(saleGroupBo.getSaleStatus());
        saleGroup.setCreatedBy("");
        saleGroup.setCreatedDate(LocalDateTime.now());
        saleGroup.setLastModifiedBy("");
        saleGroup.setLastModifiedDate(LocalDateTime.now());
        saleGroupRepository.save(saleGroup);
        List<SaleGroupItemBo> saleGroupItems = saleGroupBo.getSaleGroupItems();
        for (int i = 0; i < saleGroupItems.size(); i++) {
            SaleGroupItemBo saleGroupItemBo = saleGroupItems.get(i);
            SaleGroupItems saleGroupItem = new SaleGroupItems();
            saleGroupItem.setSaleGroupId(saleGroupID);
            saleGroupItem.setProductId(saleGroupItemBo.getId());
            saleGroupItem.setSorting(i);
            saleGroupItem.setCreatedBy("");
            saleGroupItem.setCreatedDate(LocalDateTime.now());
            saleGroupItem.setLastModifiedBy("");
            saleGroupItem.setLastModifiedDate(LocalDateTime.now());
            saleGroupItemsRepository.save(saleGroupItem);
        }
        return saleGroup;
    }

    @Transactional(rollbackFor = Exception.class)
    public SaleGroup update(String saleGroupID, SaleGroupBo saleGroupBo) {
        SaleGroup saleGroup = saleGroupRepository.getOne(saleGroupID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        saleGroup.setId(saleGroupID);
        saleGroup.setGroupName(saleGroupBo.getGroupName());
        saleGroup.setGroupImgId(saleGroupBo.getGroupImgId());
        saleGroup.setGroupImgResourceUrl(saleGroupBo.getGroupImgResourceUrl());
        saleGroup.setSaleStartDate(LocalDateTime.parse(saleGroupBo.getSaleStartDate(), formatter));
        saleGroup.setSaleEndDate(LocalDateTime.parse(saleGroupBo.getSaleEndDate(), formatter));
        saleGroup.setSaleStatus(saleGroupBo.getSaleStatus());
        saleGroup.setCreatedBy("");
        saleGroup.setCreatedDate(LocalDateTime.now());
        saleGroup.setLastModifiedBy("");
        saleGroup.setLastModifiedDate(LocalDateTime.now());
        saleGroupRepository.save(saleGroup);
        saleGroupItemsRepository.findBySaleGroupId(saleGroupID)
                .forEach(saleGroupItems -> saleGroupItemsRepository.delete(saleGroupItems));
        List<SaleGroupItemBo> saleGroupItems = saleGroupBo.getSaleGroupItems();
        for (int i = 0; i < saleGroupItems.size(); i++) {
            SaleGroupItemBo saleGroupItemBo = saleGroupItems.get(i);
            SaleGroupItems saleGroupItem = new SaleGroupItems();
            saleGroupItem.setSaleGroupId(saleGroupID);
            saleGroupItem.setProductId(saleGroupItemBo.getId());
            saleGroupItem.setSorting(i);
            saleGroupItem.setCreatedBy("");
            saleGroupItem.setCreatedDate(LocalDateTime.now());
            saleGroupItem.setLastModifiedBy("");
            saleGroupItem.setLastModifiedDate(LocalDateTime.now());
            saleGroupItemsRepository.save(saleGroupItem);
        }
        return saleGroup;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String saleGroupID) {
        saleGroupItemsRepository.findBySaleGroupId(saleGroupID)
                .forEach(saleGroupItems -> saleGroupItemsRepository.delete(saleGroupItems));
        SaleGroup saleGroup = saleGroupRepository.getOne(saleGroupID);
        saleGroupRepository.delete(saleGroup);
    }

    public SaleGroupBo getOne(String saleGroupID) {
        ModelMapper modelMapper = new ModelMapper();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        SaleGroup saleGroup = saleGroupRepository.getOne(saleGroupID);
        SaleGroupBo saleGroupBo = modelMapper.map(saleGroup, SaleGroupBo.class);
        saleGroupBo.setSaleStartDate(formatter.format(saleGroup.getSaleStartDate()));
        saleGroupBo.setSaleEndDate(formatter.format(saleGroup.getSaleEndDate()));
        List<SaleGroupItemBo> saleGroupItems = saleGroupItemsRepository.findBySaleGroupIdOrderBySortingAsc(saleGroupID)
                .stream().map(saleGroupItem -> {
                    SaleGroupItemBo saleGroupItemBo = modelMapper.map(saleGroupItem, SaleGroupItemBo.class);
                    Product product = productRepository.getOne(saleGroupItem.getProductId());
                    saleGroupItemBo.setProductName(product.getProductName());
                    saleGroupItemBo.setProductImgResourceUrl(product.getProductImgResourceUrl());
                    saleGroupItemBo.setSalePrice(product.getSalePrice());
                    saleGroupItemBo.setDiscPrice(product.getDiscPrice());
                    saleGroupItemBo.setStockQuantity(productItemRepository.findByProductIdStockQuantitySum(product.getId()));
                    return saleGroupItemBo;
                }).collect(Collectors.toList());
        saleGroupBo.setSaleGroupItems(saleGroupItems);
        return saleGroupBo;
    }
    
    /**
     * 入口網站銷售
     * @return
     */
    public SaleGroupBo getSaleOnPortal() {
    	SaleGroupBo saleGroupBo = new SaleGroupBo();
        saleGroupBo.setGroupImgResourceUrl(detaultGroupImgResourceUrl);
        List<Product> productList = productRepository.getSaleOnPortal();
        List<SaleGroupItemBo> saleGroupItems = productList.stream().map( product -> {
        	SaleGroupItemBo saleGroupItemBo = new SaleGroupItemBo();
        	saleGroupItemBo.setId(product.getId());
        	saleGroupItemBo.setProductName(product.getProductName());
            saleGroupItemBo.setProductImgResourceUrl(product.getProductImgResourceUrl());
            saleGroupItemBo.setSalePrice(product.getSalePrice());
            saleGroupItemBo.setDiscPrice(product.getDiscPrice());
            saleGroupItemBo.setStockQuantity(productItemRepository.findByProductIdStockQuantitySum(product.getId()));
            return saleGroupItemBo;
        }).collect(Collectors.toList());
        saleGroupBo.setSaleGroupItems(saleGroupItems);
		return saleGroupBo;
    }
}
