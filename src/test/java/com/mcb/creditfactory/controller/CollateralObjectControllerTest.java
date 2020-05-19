package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.repository.CarValueRepository;
import com.mcb.creditfactory.service.CollateralService;
import com.mcb.creditfactory.service.car.CarService;
import com.mcb.creditfactory.util.CarUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.mcb.creditfactory.testdata.CarTestData.*;
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

    @Test
    public void save() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCarDtoAsJson()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("1", getAsString(mvcResult));
    }

    @Test
    public void getInfo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCarDtoAsJson()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("{\"type\":\"car\",\"id\":1,\"brand\":\"BMW\",\"model\":\"x5\",\"power\":220.0," +
                "\"year\":2008,\"value\":2000000,\"type\":\"CAR\",\"date\":\"" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()) +
    "\"}", getAsString(mvcResult));
    }

    @Test
    public void toEstimate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/estimate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCarDtoAsJson()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("1", getAsString(mvcResult));
    }

    private String getAsString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }
}