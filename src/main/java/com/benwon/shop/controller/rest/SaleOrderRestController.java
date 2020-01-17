package com.benwon.shop.controller.rest;

import com.benwon.shop.bo.SaleOrderIsShippedBo;
import com.benwon.shop.bo.ShopCartBo;
import com.benwon.shop.entity.SaleOrder;
import com.benwon.shop.service.SaleOrderService;
import com.benwon.shop.service.TokenService;
import com.benwon.shop.vo.SaleOrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class SaleOrderRestController {

	private final SaleOrderService saleOrderService;
	private final TokenService tokenService;

	// 查詢單一訂單
	@GetMapping(path = "/v1/saleOrder/{id}")
	public SaleOrderVo getDetail(@PathVariable("id") String id) {
		SaleOrderVo saleOrderVo = saleOrderService.getDetail(id);
		return saleOrderVo;
	}

	// 更新出貨狀態
	@PatchMapping(path = "/v1/saleOrder/{id}/isShipped")
	public void updateIsShipped(@PathVariable("id") String id, @RequestBody SaleOrderIsShippedBo saleOrderIsShippedBo) {
		if (new Integer(1).equals(saleOrderIsShippedBo.getIsShipped())) {
			// 更新已出貨
			saleOrderService.updateIsShipped(id, saleOrderIsShippedBo);
		} else {
			// TODO: 還沒規劃
		}
	}

	/**
	 * 建立訂單
	 * 
	 * @param authorization
	 * @param shopCart
	 * @return
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/v1/saleOrder/shopCart")
	public SaleOrder createSaleOrder(@RequestHeader("Authorization") String authorization,
			@RequestBody ShopCartBo shopCart) {

		String uid = tokenService.verify(authorization);

		SaleOrder saleOrder = saleOrderService.createByShopCart(uid, shopCart);
		return saleOrder;
	}
}
