package com.benwon.shop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benwon.shop.bo.ProductBo;
import com.benwon.shop.bo.SalePortalUpdateBo;
import com.benwon.shop.entity.SalePortal;
import com.benwon.shop.repository.SalePortalRepository;

import lombok.RequiredArgsConstructor;
import xyz.downgoon.snowflake.Snowflake;

@Service
@RequiredArgsConstructor
public class SalePortalService {
	private final Snowflake snowflake;
	private final SalePortalRepository salePortalRepository;

	@Transactional(rollbackFor = Exception.class)
	public void update(SalePortalUpdateBo salePortalUpdateBo) {
		salePortalRepository.deleteAll();

		List<ProductBo> salePortalList = salePortalUpdateBo.getSalePortalList();
		for (int i = 0; i < salePortalList.size(); i++) {
			ProductBo productBo = salePortalList.get(i);
			SalePortal salePortal = new SalePortal();
			salePortal.setId(String.valueOf(snowflake.nextId()));
			salePortal.setProductId(productBo.getId());
			salePortal.setSorting(i);
			salePortal.setSaleStartDate(LocalDateTime.now());
			salePortal.setSaleEndDate(LocalDateTime.now().plusYears(1));
			salePortal.setSaleStatus(1);
			salePortal.setCreatedBy("");
			salePortal.setCreatedDate(LocalDateTime.now());
			salePortal.setLastModifiedBy("");
			salePortal.setLastModifiedDate(LocalDateTime.now());
			salePortalRepository.save(salePortal);
		}

	}
}