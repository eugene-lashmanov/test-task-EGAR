package com.mcb.creditfactory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarValueRepository;
import com.mcb.creditfactory.service.CollateralService;
import com.mcb.creditfactory.service.car.CarService;
import com.mcb.creditfactory.util.CarUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CollateralObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CollateralService collateralService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarValueRepository carValueRepository;

    @Test
    public void save() throws Exception {
        Car car = new Car(1L, "BMW", "x5", 220.0,
                (Short.parseShort("2008")));
        CarDto carDto = new CarDto(1L, "BMW", "x5", 220.0,
                Short.parseShort("2008"), BigDecimal.valueOf(2000000));
        collateralService.saveCollateral(carDto);
        Assert.assertEquals(car, carService.load(car.getId()).orElse(null));
        Assert.assertEquals(BigDecimal.valueOf(2000000), CarUtil.getLastValue(carValueRepository.findAllByCarId(car.getId())));


        String json = new ObjectMapper().writeValueAsString(carDto);
        MvcResult mvcResult = mockMvc.perform(post("/collateral/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("1", content);
    }

    @Test
    public void getInfo() {

    }

    @Test
    public void toEstimate() {
    }
}