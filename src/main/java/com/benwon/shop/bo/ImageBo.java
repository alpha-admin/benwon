package com.benwon.shop.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "resourceUrl"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageBo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("resourceUrl")
    private String resourceUrl;
}
