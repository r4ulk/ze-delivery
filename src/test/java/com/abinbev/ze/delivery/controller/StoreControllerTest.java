package com.abinbev.ze.delivery.controller;

import com.abinbev.ze.delivery.service.StoreService;
import com.abinbev.ze.delivery.utils.StoreUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreControllerTest {

    private static final String PARAM_LONGITUDE = "lng";
    private static final String PARAM_LATITUDE  = "lat";

    private static final Double LONGITUDE =-43.297337;
    private static final Double LATITUDE  =-23.013538;

    @Autowired
    private StoreService service;

    private MockMvc mockMvc;

    @Autowired
    private StoreController storeController;

    @BeforeEach
    public void setup() {
        service.deleteById(StoreUtils.STORE_VALID_NEW_ID);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.storeController)
                .build();
    }

    @AfterEach
    public void finalizer() {
        service.deleteById(StoreUtils.STORE_VALID_NEW_ID);
    }

    @Test
    public void getStoreByIdController_whenValidStoreId_thenReturnValidStore() throws Exception {
        mockMvc.perform(get(StoreController.BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tradingName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tradingName").value(StoreUtils.STORE_TRADING_NAME));
    }

    @Test
    public void getStoreByIdController_whenInvalidStoreId_thenReturnStoreNotFoundException() throws Exception {
        mockMvc.perform(get(StoreController.BASE_URL + "/10000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createStoreController_whenValidStore_thenReturnValidStore() throws Exception {
        mockMvc.perform(
                post(StoreController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(StoreUtils.getValidStoreMock())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tradingName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tradingName").value(StoreUtils.STORE_TRADING_NAME))
                .andExpect(jsonPath("$.document").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.document").value(StoreUtils.STORE_VALID_NEW_DOCUMENT));
    }

    @Test
    public void createStoreController_whenDuplicatedStore_thenReturnStoreDuplicatedException() throws Exception {
        mockMvc.perform(
                post(StoreController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(StoreUtils.getDuplicatedStoreMock())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getAllNearStoresByLocation_whenSuccess_thenReturnStoreList() throws Exception {
        mockMvc.perform(
                get(StoreController.BASE_URL)
                        .param(PARAM_LONGITUDE, LONGITUDE.toString())
                        .param(PARAM_LATITUDE, LATITUDE.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tradingName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tradingName").value(StoreUtils.STORE_TRADING_NAME))
                .andExpect(jsonPath("$[0].document").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].document").value(StoreUtils.STORE_VALID_DOCUMENT));
    }

}
