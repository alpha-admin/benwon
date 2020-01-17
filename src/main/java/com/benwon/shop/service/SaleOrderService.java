package com.benwon.shop.service;

import com.benwon.shop.bo.SaleOrderIsShippedBo;
import com.benwon.shop.bo.ShopCartBo;
import com.benwon.shop.bo.ShopCartItemBo;
import com.benwon.shop.common.contract.IsShippedEnum;
import com.benwon.shop.common.contract.SalesUnitEnum;
import com.benwon.shop.entity.Product;
import com.benwon.shop.entity.ProductItem;
import com.benwon.shop.entity.SaleOrder;
import com.benwon.shop.entity.SaleOrderDetail;
import com.benwon.shop.exception.BenwonConflictException;
import com.benwon.shop.repository.ProductItemRepository;
import com.benwon.shop.repository.ProductRepository;
import com.benwon.shop.repository.SaleOrderDetailRepository;
import com.benwon.shop.repository.SaleOrderRepository;
import com.benwon.shop.vo.SaleOrderDetailVo;
import com.benwon.shop.vo.SaleOrderVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.downgoon.snowflake.Snowflake;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleOrderService {
	private final Snowflake snowflake;
	private final SaleOrderRepository saleOrderRepository;
	private final SaleOrderDetailRepository saleOrderDetailRepository;
	private final ProductRepository productRepository;
	private final ProductItemRepository productItemRepository;

	private static Object syncObject = new Object();
	
	
	

	public SaleOrderVo getDetail(String id) {
		ModelMapper modelMapper = new ModelMapper();
		SaleOrder saleOrder = saleOrderRepository.getOne(id);
		SaleOrderVo saleOrderVo = modelMapper.map(saleOrder, SaleOrderVo.class);
		List<SaleOrderDetailVo> saleOrderDetailVoList = new ArrayList<>();
		List<SaleOrderDetail> saleOrderDetailList = saleOrderDetailRepository
				.findByOrderIdOrderByCreatedByAsc(saleOrderVo.getId());
		for (SaleOrderDetail saleOrderDetail : saleOrderDetailList) {
			Product product = productRepository.getOne(saleOrderDetail.getProductId());
			SaleOrderDetailVo saleOrderDetailVo = modelMapper.map(saleOrderDetail, SaleOrderDetailVo.class);
			saleOrderDetailVo.setProductName(product.getProductName());
			saleOrderDetailVoList.add(saleOrderDetailVo);
		}
		saleOrderVo.setSaleOrderDetailList(saleOrderDetailVoList);
		return saleOrderVo;
	}

	public void updateIsShipped(String id, SaleOrderIsShippedBo saleOrderIsShippedBo) {
		SaleOrder saleOrder = saleOrderRepository.getOne(id);
		saleOrder.setIsShipped(saleOrderIsShippedBo.getIsShipped());
		saleOrderRepository.save(saleOrder);
		List<SaleOrderDetail> saleOrderDetailList = saleOrderDetailRepository.findByOrderIdOrderByCreatedByAsc(id);
		for (SaleOrderDetail saleOrderDetail : saleOrderDetailList) {
			saleOrderDetail.setIsShipped(saleOrderIsShippedBo.getIsShipped());
			saleOrderDetailRepository.save(saleOrderDetail);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	synchronized public SaleOrder createByShopCart(String uid, ShopCartBo shopCartBo) {
		String saleOrderId = String.valueOf(snowflake.nextId());
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setId(saleOrderId);
		saleOrder.setPurchaserUserId(uid);
		saleOrder.setShowPurchaserUser(shopCartBo.getShowPurchaserUser() == true ? 1 : 0);
		saleOrder.setReceiverName(shopCartBo.getReceiverName());
		saleOrder.setMobilePhone(shopCartBo.getMobilePhone());
		saleOrder.setCounty(shopCartBo.getCounty());
		saleOrder.setDistrict(shopCartBo.getDistrict());
		saleOrder.setZipcode(shopCartBo.getZipcode());
		saleOrder.setReceiverAddress(shopCartBo.getReceiverAddress());
		saleOrder.setRemark(shopCartBo.getRemark());

		Integer consumptionAmount = 0; // 消費金額
		Integer freightAmount = 0; // 運費
		Integer orderAmount = 0; // 訂單金額
		// 明細
		List<ShopCartItemBo> shopList = shopCartBo.getShopList();
		for (ShopCartItemBo shopCartItemBo : shopList) {
			Integer subtotal = 0;
			ProductItem productItem = productItemRepository.getOne(shopCartItemBo.getProductItemId());
			Product product = productRepository.getOne(productItem.getProductId());
			subtotal = product.getDiscPrice() * shopCartItemBo.getBuyCount();
			consumptionAmount = consumptionAmount + subtotal;

			if ((productItem.getStockQuantity() - shopCartItemBo.getBuyCount()) < 0) {
				throw new BenwonConflictException(
						String.format("%s - %s 庫存不足", product.getProductName(), productItem.getProductItemName()));
			}

			//
			SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
			saleOrderDetail.setOrderId(saleOrderId);
			saleOrderDetail.setProductId(product.getId());
			saleOrderDetail.setProductName(product.getProductName());
			saleOrderDetail.setProductItemId(productItem.getId());
			saleOrderDetail.setProductItemName(productItem.getProductItemName());
			saleOrderDetail.setSalesUnit(0);
			saleOrderDetail.setSalesUnitDesc(product.getBasicUnitDesc());
			saleOrderDetail.setUnitCount(shopCartItemBo.getBuyCount());
			saleOrderDetail.setSalesUnitDesc(shopCartItemBo.getBasicUnitDesc());
			saleOrderDetail.setSubtotal(subtotal);
			saleOrderDetail.setIsShipped(IsShippedEnum.NONE.getCode());
			saleOrderDetail.setRefSaleGroupId("");
			saleOrderDetail.setCreatedBy("");
			saleOrderDetail.setCreatedDate(LocalDateTime.now());
			saleOrderDetail.setLastModifiedBy("");
			saleOrderDetail.setLastModifiedDate(LocalDateTime.now());
			saleOrderDetailRepository.save(saleOrderDetail);
			// 減掉庫存
			productItem.setStockQuantity(productItem.getStockQuantity() - shopCartItemBo.getBuyCount());
			productItemRepository.save(productItem);
		}
		if (consumptionAmount < 2500) {
			freightAmount = 80;
		} else {
			freightAmount = 0;
		}
		orderAmount = consumptionAmount + freightAmount;
		saleOrder.setConsumptionAmount(consumptionAmount);
		saleOrder.setFreightAmount(freightAmount);
		saleOrder.setOrderAmount(orderAmount);
		saleOrder.setFreightAmount(shopCartBo.getFreightAmount());
		saleOrder.setIsShipped(IsShippedEnum.NONE.getCode());
		saleOrder.setPayState(0);
		saleOrder.setCreatedBy(uid);
		saleOrder.setCreatedDate(LocalDateTime.now());
		saleOrder.setLastModifiedBy(uid);
		saleOrder.setLastModifiedDate(LocalDateTime.now());
		saleOrder = saleOrderRepository.save(saleOrder);
		return saleOrder;
	}
}
