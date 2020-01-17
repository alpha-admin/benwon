package com.benwon.shop.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "groupName",
        "groupImgId",
        "groupImgResourceUrl",
        "saleStartDate",
        "saleEndDate",
        "saleStatus",
        "saleGroupItems"
})
@Data
public class SaleGroupBo {
    @JsonProperty("groupName")
    private String groupName;
    @JsonProperty("groupImgId")
    private Long groupImgId;
    @JsonProperty("groupImgResourceUrl")
    private String groupImgResourceUrl;
    @JsonProperty("saleStartDate")
    private String saleStartDate;
    @JsonProperty("saleEndDate")
    private String saleEndDate;
    @JsonProperty("saleStatus")
    private Integer saleStatus;
    @JsonProperty("saleGroupItems")
    private List<SaleGroupItemBo> saleGroupItems = null;
}
