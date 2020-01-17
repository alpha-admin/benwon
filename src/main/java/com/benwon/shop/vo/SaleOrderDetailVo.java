package com.benwon.shop.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleOrderDetailVo {
    private Long id;
    private String orderId;
    private String productId;
    private String productName;
    private Integer unitCount;
    private Integer subtotal;
    private Integer isShipped;
    private String refSaleGroupId;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
