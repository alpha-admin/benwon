package com.benwon.shop.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benwon.shop.bo.ProductBo;
import com.benwon.shop.bo.ShopCartBo;
import com.benwon.shop.bo.ShopCartItemBo;
import com.benwon.shop.repository.ProductItemRepository;
import com.benwon.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class ShopCartRestController {

	private final ProductService productService;
	private final ProductItemRepository productItemRepository;

//	@PostMapping(path = "/v1/shopCart/calculate")
//	public ShopCartBo calculate(@RequestBody ShopCartBo shopCart) {
//		List<ShopCartItemBo> shopList = shopCart.getShopList();
//		Integer orderAmount = 0;
//		Integer saleAmount = 0;
//		for (ShopCartItemBo shopCartItemBo : shopList) {
//			ProductBo productBo = productService.getDetail(shopCartItemBo.getProductId());
//			saleAmount += (shopCartItemBo.getBuyCount() * productBo.getDiscPrice());
//			shopCartItemBo.setSubTotal(saleAmount);
//		}
//		shopCart.setSaleAmount(saleAmount);
//		if(orderAmount < 2500) {
//			shopCart.setFreightAmount(80);
//		}
//		shopCart.setOrderAmount(shopCart.getSaleAmount() + shopCart.getFreightAmount());
//		return shopCart;
//	}
}
