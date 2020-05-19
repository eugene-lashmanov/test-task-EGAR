package com.mcb.creditfactory;

import com.mcb.creditfactory.model.Value;

import java.math.BigDecimal;
import java.util.List;

public class Util {

    private Util(){
    }

    public static <T extends Value> BigDecimal getLastValue(List<T> values) {
        BigDecimal value = null;
        if (!values.isEmpty()) {
            value = values.stream().findFirst().get().getValue();
        }
        return value;
    }
}
