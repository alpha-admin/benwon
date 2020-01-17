package com.benwon.shop.bo;

import lombok.Data;

@Data
public class UserProfileBo {
    private String realName;
    private String lineId;
    private String lineUserName;
    private String mobilePhone;
    private String email;
    private String county;
    private String district;
    private String zipcode;
    private String address;
}
