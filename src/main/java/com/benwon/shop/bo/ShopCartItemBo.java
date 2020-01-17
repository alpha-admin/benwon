package com.benwon.shop.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "shopItemId", "isEnable", "productId", "productName", "productImgResourceUrl", "productItemId",
		"productItemName", "discPrice", "expiryDate", "buyCount", "basicUnitDesc", "subTotal" })
@Data
public class ShopCartItemBo {
	@JsonProperty("shopItemId")
	private String shopItemId;
	@JsonProperty("isEnable")
	private Boolean isEnable;
	@JsonProperty("productId")
	private String productId;
	@JsonProperty("productName")
	private String productName;
	@JsonProperty("productImgResourceUrl")
	private String productImgResourceUrl;
	@JsonProperty("productItemId")
	private String productItemId;
	@JsonProperty("productItemName")
	private String productItemName;
	@JsonProperty("discPrice")
	private Integer discPrice;
	@JsonProperty("expiryDate")
	private String expiryDate;
	@JsonProperty("buyCount")
	private Integer buyCount;
	@JsonProperty("basicUnitDesc")
	private String basicUnitDesc;
	@JsonProperty("subTotal")
	private Integer subTotal;
}
