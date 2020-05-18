package com.mcb.creditfactory.util;

import com.mcb.creditfactory.model.CarValue;

import java.math.BigDecimal;
import java.util.List;

public class CarUtil {

    private CarUtil(){
    }

    public static BigDecimal getLastValue(List<CarValue> carValues) {
        BigDecimal value = null;
        if (!carValues.isEmpty()) {
            value = carValues.stream().findFirst().get().getValue();
        }
        return value;
    }
}
