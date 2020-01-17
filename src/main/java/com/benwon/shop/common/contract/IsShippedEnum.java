package com.benwon.shop.common.contract;

public enum IsShippedEnum {

    NONE(0), SHIPPED(1);

    private Integer code;

    IsShippedEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
