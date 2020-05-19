package com.mcb.creditfactory.service;


import com.mcb.creditfactory.repository.CarValueRepository;
import com.mcb.creditfactory.service.car.CarService;
import com.mcb.creditfactory.util.CarUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.mcb.creditfactory.testdata.CarTestData.*;
import static com.mcb.creditfactory.testdata.CarTestData.CAR;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CollateralService collateralService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarValueRepository carValueRepository;

    @Before
    public void before() {
        collateralService.saveCollateral(CAR_DTO);
    }

    @Test
    public void saveNotApproved() {
        Assert.assertNull(collateralService.saveCollateral(CAR_DTO_NOT_APPROVED));
    }

    @Test
    public void saveIsPresent() {
        Assert.assertEquals(CAR, carService.load(CAR.getId()).orElse(null));
    }

    @Test
    public void saveChangedValue() throws InterruptedException {
        Thread.sleep(1000);
        collateralService.estimate(CAR_DTO_TO_ESTIMATE);
        Assert.assertEquals(BigDecimal.valueOf(1500000), CarUtil.getLastValue(carValueRepository.findAllByCarId(CAR.getId())));
    }
}
