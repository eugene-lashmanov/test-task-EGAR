package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.repository.CarValueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CollateralObjectControllerTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarValueRepository carValueRepository;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void save() {
    }

    @Test
    public void getInfo() {
    }

    @Test
    public void toEstimate() {
    }
}