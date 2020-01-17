package com.benwon.shop.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.benwon.shop.dto.SaleOrderDetailDto;
import com.benwon.shop.dto.SaleOrderDto;
import com.benwon.shop.entity.SaleOrder;
import com.benwon.shop.entity.SaleOrderDetail;
import com.benwon.shop.repository.SaleOrderDetailRepository;
import com.benwon.shop.repository.SaleOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeService {

	private final SaleOrderRepository saleOrderRepository;
	private final SaleOrderDetailRepository saleOrderDetailRepository;

	public List<SaleOrderDto> getAllSaleOrder(String uid) {
		List<SaleOrder> saleOrders = saleOrderRepository.findByPurchaserUserIdOrderByCreatedDateDesc(uid);
		List<SaleOrderDto> saleOrderDtos = new ModelMapper().map(saleOrders, new TypeToken<List<SaleOrderDto>>() {
		}.getType());
		for (SaleOrderDto saleOrderDto : saleOrderDtos) {
			List<SaleOrderDetail> saleOrderDetails = saleOrderDetailRepository
					.findByOrderIdOrderByIdAsc(saleOrderDto.getId());
			List<SaleOrderDetailDto> saleOrderDetailDtos = new ModelMapper().map(saleOrderDetails,
					new TypeToken<List<SaleOrderDetail>>() {
					}.getType());
			saleOrderDto.setSaleOrderDetails(saleOrderDetailDtos);
		}
		return saleOrderDtos;
	}

}
