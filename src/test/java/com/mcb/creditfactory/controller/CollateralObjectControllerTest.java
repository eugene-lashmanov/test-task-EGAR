package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.service.CollateralService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.mcb.creditfactory.JsonUtil.getAsJson;
import static com.mcb.creditfactory.testdata.AirplaneTestData.AIRPLANE_DTO;
import static com.mcb.creditfactory.testdata.CarTestData.CAR_DTO;
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

    @Before
    public void before() {
        collateralService.saveCollateral(CAR_DTO);
        collateralService.saveCollateral(AIRPLANE_DTO);
    }

    @Test
    public void saveCar() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getAsJson(CAR_DTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("1", getAsString(mvcResult));
    }

    @Test
    public void saveAirplane() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getAsJson(AIRPLANE_DTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("1", getAsString(mvcResult));
    }

    @Test
    public void getInfoCar() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getAsJson(CAR_DTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("{\"type\":\"car\",\"id\":1,\"brand\":\"BMW\",\"model\":\"x5\",\"power\":220.0," +
                "\"year\":2008,\"value\":2000000,\"type\":\"CAR\",\"date\":\"" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()) +
                "\"}", getAsString(mvcResult));
    }

    @Test
    public void getInfoAirplane() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getAsJson(AIRPLANE_DTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        System.out.println(getAsString(mvcResult));
        Assert.assertEquals("{\"type\":\"airplane\",\"id\":1,\"brand\":\"Boeing\",\"model\":\"747\"," +
                "\"manufacturer\":\"China\",\"year\":2015,\"fuelCapacity\":300000,\"seats\":300,\"value\":250000000," +
                "\"type\":\"AIRPLANE\",\"date\":\"" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()) +
                "\"}", getAsString(mvcResult));
    }

    @Test
    public void toEstimateCar() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/estimate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getAsJson(CAR_DTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("1", getAsString(mvcResult));
    }

    @Test
    public void toEstimateAirplane() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/collateral/estimate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getAsJson(AIRPLANE_DTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Assert.assertEquals("1", getAsString(mvcResult));
    }

    private String getAsString(MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }
}