package com.benwon.shop.bo;

import com.benwon.shop.vo.SalesUnitVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "productId",
        "productItemName",
        "productImgResourceUrl",
        "stockQuantity",
        "sorting",
        "salesUnit"
})
@Data
public class ProductItemBo {
    private String id;
    private String productId;
    private String productItemName;
    private String productImgResourceUrl;
    private Integer stockQuantity;
    private Integer sorting;
    private Integer buyCount;
    private Integer salesUnitselected = 1;
    private List<SalesUnitVo> salesUnitList;
}
