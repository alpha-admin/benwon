package com.benwon.shop.common.converter;

import com.benwon.shop.common.contract.SalesUnitEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SalesUnitConverter implements AttributeConverter<SalesUnitEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SalesUnitEnum salesUnitEnum) {
        if (salesUnitEnum == null) {
            return null;
        }
        return salesUnitEnum.getCode();
    }

    @Override
    public SalesUnitEnum convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(SalesUnitEnum.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
