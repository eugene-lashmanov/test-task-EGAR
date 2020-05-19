package com.mcb.creditfactory.util;


import com.mcb.creditfactory.model.AirplaneValue;

import java.math.BigDecimal;
import java.util.List;

public class AirplaneUtil {

    private AirplaneUtil() {
    }

    public static BigDecimal getLastValue(List<AirplaneValue> airplaneValues) {
        BigDecimal value = null;
        if (!airplaneValues.isEmpty()) {
            value = airplaneValues.stream().findFirst().get().getValue();
        }
        return value;
    }
}
