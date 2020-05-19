package com.mcb.creditfactory.service;


import com.mcb.creditfactory.repository.AirplaneValueRepository;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.util.AirplaneUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.mcb.creditfactory.testdata.AirplaneTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirplaneServiceTest {

    @Autowired
    private CollateralService collateralService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private AirplaneValueRepository airplaneValueRepository;

    @Before
    public void before() {
        collateralService.saveCollateral(AIRPLANE_DTO);
    }

    @Test
    public void saveNotApproved() {
        Assert.assertNull(collateralService.saveCollateral(AIRPLANE_DTO_NOT_APPROVED));
    }

    @Test
    public void saveIsPresent() {
        Assert.assertEquals(AIRPLANE, airplaneService.load(AIRPLANE.getId()).orElse(null));
    }

    @Test
    public void saveChangedValue() throws InterruptedException {
        Thread.sleep(1000);
        collateralService.estimate(AIRPLANE_DTO_TO_ESTIMATE);
        Assert.assertEquals(BigDecimal.valueOf(240000000), AirplaneUtil.getLastValue(airplaneValueRepository.findAllByAirplaneId(AIRPLANE.getId())));
    }
}
