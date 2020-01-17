package com.benwon.shop.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "productName", "productImgId", "productImgResourceUrl", "salePrice", "discPrice",
		"expiryDate", "basicUnitDesc", "productItemList", "norm", "productIntroduction", "introduction",
		"specifications", "imageList" })
@Data
public class ProductBo {
	@JsonProperty("id")
	private String id;
	@JsonProperty("productName")
	private String productName;
	@JsonProperty("productImgId")
	private Long productImgId;
	@JsonProperty("productImgResourceUrl")
	private String productImgResourceUrl;
	@JsonProperty("salePrice")
	private Integer salePrice;
	@JsonProperty("discPrice")
	private Integer discPrice; // 優惠價
	@JsonProperty("expiryDate")
	private String expiryDate;
	@JsonProperty("basicUnitDesc")
	private String basicUnitDesc;
	@JsonProperty("norm")
	private String norm;
	@JsonProperty("productIntroduction")
	private String productIntroduction;
	@JsonProperty("introduction")
	private String introduction;
	@JsonProperty("specifications")
	private String specifications;
	private String createdBy;
	private LocalDateTime createdDate;
	private String lastModifiedBy;
	private LocalDateTime lastModifiedDate;

	@JsonProperty("productItemList")
	private List<ProductItemBo> productItemList = null;
	@JsonProperty("imageList")
	private List<ImageBo> imageList = null;

	// 庫存總數量
	private Integer stockQuantitySum;
}
