package com.benwon.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "displayName",
        "pictureUrl"
})
@Data
public class LineProfileDto {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("pictureUrl")
    private String pictureUrl;
}
