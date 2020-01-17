package com.benwon.shop.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id"
})
@Data
public class SaleGroupItemBo {
    @JsonProperty("id")
    private String id;
    private String productName;
    private String productImgResourceUrl;
    private Integer salePrice;
    private Integer discPrice;
    private Integer stockQuantity;
}
