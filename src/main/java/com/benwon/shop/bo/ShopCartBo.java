package com.benwon.shop.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "version", "showPurchaserUser", "receiverName", "mobilePhone", "county", "district", "zipcode",
		"receiverAddress", "remark", "subTotal", "orderAmount", "freightAmount", "shopList"
})
@Data
public class ShopCartBo {
	@JsonProperty("version")
	private Integer version;
	@JsonProperty("showPurchaserUser")
	private Boolean showPurchaserUser;
	@JsonProperty("receiverName")
	private String receiverName;
	@JsonProperty("mobilePhone")
	private String mobilePhone;
	@JsonProperty("county")
	private String county;
	@JsonProperty("district")
	private String district;
	@JsonProperty("zipcode")
	private String zipcode;
	@JsonProperty("receiverAddress")
	private String receiverAddress;
	@JsonProperty("remark")
	private String remark;
	@JsonProperty("subTotal")
	private Integer subTotal;
	@JsonProperty("freightAmount")
	private Integer freightAmount;
	@JsonProperty("orderAmount")
	private Integer orderAmount;
	@JsonProperty("shopList")
	private List<ShopCartItemBo> shopList = null;
}
