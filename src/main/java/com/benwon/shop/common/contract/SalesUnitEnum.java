package com.benwon.shop.common.contract;

public enum SalesUnitEnum {

    BASIC(1), SET(2);

    private Integer code;

    SalesUnitEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
