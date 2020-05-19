package com.mcb.creditfactory.testdata;


import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.model.Airplane;

import java.math.BigDecimal;

public class AirplaneTestData {

    public static final Airplane AIRPLANE = new Airplane(1L, "Boeing", "747", "China",
            Short.parseShort("2015"), 300000, 300);

    public static final AirplaneDto AIRPLANE_DTO = new AirplaneDto(1L, "Boeing", "747", "China",
            Short.parseShort("2015"), 300000, 300, BigDecimal.valueOf(250000000));

    public static final AirplaneDto AIRPLANE_DTO_NOT_APPROVED = new AirplaneDto(1L, "Boeing", "747", "China",
            Short.parseShort("1945"), 300000, 300, BigDecimal.valueOf(250000000));

    public static final AirplaneDto AIRPLANE_DTO_TO_ESTIMATE = new AirplaneDto(1L, "Boeing", "747", "China",
            Short.parseShort("1945"), 300000, 300, BigDecimal.valueOf(240000000));

    private AirplaneTestData() {

    }


}
