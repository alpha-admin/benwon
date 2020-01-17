package com.benwon.shop.dto.line;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iss",
        "sub",
        "aud",
        "exp",
        "iat",
        "amr",
        "name",
        "picture"
})
@Data
public class LineIdToken {
    @JsonProperty("iss")
    private String iss; // https://access.line.me. URL where the ID token is generated.
    @JsonProperty("sub")
    private String sub; // User ID for which the ID token is generated
    @JsonProperty("aud")
    private String aud; // Channel ID
    @JsonProperty("exp")
    private Integer exp; // The expiry date of the token in UNIX time.
    @JsonProperty("iat")
    private Integer iat; // Time when the ID token was generated in UNIX time.
    /**
     * amr
     * pwd 使用電子郵件和密碼登錄
     * lineautologin LINE自動登錄（包括通過LINE SDK）
     * lineqr 使用QR碼登錄
     * linesso 使用單點登錄登錄
     */
    @JsonProperty("amr")
    private List<String> amr = null;
    @JsonProperty("name")
    private String name; // 用戶的顯示名稱
    /**
     * picture
     * 用戶的個人資料圖片網址。如果profile未在授權請求中指定範圍，則不包括在內。
     */
    @JsonProperty("picture")
    private String picture; // 個人圖檔
}
