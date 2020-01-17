package com.benwon.shop.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 銷售明細
 * 
 * @author sam
 *
 */
@Data
public class SaleOrderDetailDto {
	private Long id;
	private String orderId;
	private String productId;
	private String productName;
	private String productItemId;
	private String productItemName;
	private Integer salesUnit;
	private String salesUnitDesc;
	private Integer unitCount;
	private Integer subtotal;
	private Integer isShipped;
	private String refSaleGroupId;
	private String createdBy;
	private LocalDateTime createdDate;
	private String lastModifiedBy;
	private LocalDateTime lastModifiedDate;
}
