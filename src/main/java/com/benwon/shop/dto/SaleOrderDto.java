package com.benwon.shop.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * 銷售訂單資訊
 * 
 * @author sam
 *
 */
@Data
public class SaleOrderDto {
	private String id;
	private String purchaserUserId;
	private String receiverName;
	private String mobilePhone;
	private String county;
	private String district;
	private String zipcode;
	private String receiverAddress;
	private String remark;
	private Integer consumptionAmount;
	private Integer freightAmount;
	private Integer orderAmount;
	private Integer payState;
	private Integer isShipped;
	private Integer showPurchaserUser;
	private String createdBy;
	private LocalDateTime createdDate;
	private String lastModifiedBy;
	private LocalDateTime lastModifiedDate;

	private List<SaleOrderDetailDto> saleOrderDetails;
}
