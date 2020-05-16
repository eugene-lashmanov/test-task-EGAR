package com.mcb.creditfactory.service.airplane;


import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {

    private AirplaneDto airplaneDto;

    @Override
    public BigDecimal getValue() {
        return airplaneDto.getValue();
    }

    @Override
    public Short getYear() {
        return airplaneDto.getYear();
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }
}
