package com.benwon.shop.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "salePortalList"
})
@Data
public class SalePortalUpdateBo {
    @JsonProperty("salePortalList")
    private List<ProductBo> salePortalList = null;
}
