package com.mcb.creditfactory.service.collateralObject;


import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class CollateralObjectAdapter implements CollateralObject {
    Collateral collateral;

    @Override
    public BigDecimal getValue() {
        return collateral.getValue();
    }

    @Override
    public Short getYear() {
        return collateral.getYear();
    }

    @Override
    public LocalDate getDate() {
        return collateral.getDate();
    }

    @Override
    public CollateralType getType() {
        return collateral.getType();
    }
}
