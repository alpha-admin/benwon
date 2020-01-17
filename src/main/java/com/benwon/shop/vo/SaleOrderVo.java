package com.benwon.shop.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleOrderVo {
    private String id;
    private String purchaserUserId;
    private String receiverName;
    private String mobilePhone;
    private String zipcode;
    private String receiverAddress;
    private String remark;
    private Integer orderAmount;
    private Integer freightAmount;
    private Integer isShipped;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private List<SaleOrderDetailVo> saleOrderDetailList;
}
