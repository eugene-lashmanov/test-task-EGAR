package com.mcb.creditfactory.testdata;


import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.model.Car;

import java.math.BigDecimal;

public class CarTestData {

    public static final Car CAR = new Car(1L, "BMW", "x5", 220.0,
            (Short.parseShort("2008")));

    public static final CarDto CAR_DTO = new CarDto(1L, "BMW", "x5", 220.0,
            Short.parseShort("2008"), BigDecimal.valueOf(2000000));

    public static final CarDto CAR_DTO_NOT_APPROVED = new CarDto(1L, "BMW", "x5", 220.0,
            Short.parseShort("2008"), BigDecimal.valueOf(900000));

    public static final CarDto CAR_DTO_TO_ESTIMATE = new CarDto(1L, "BMW", "x5", 220.0,
            Short.parseShort("2008"), BigDecimal.valueOf(1500000));

    private CarTestData() {

    }

}
