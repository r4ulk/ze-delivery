package com.abinbev.ze.delivery.controller;

import com.abinbev.ze.delivery.exception.StoreDuplicatedException;
import com.abinbev.ze.delivery.exception.StoreNotFoundException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.service.StoreService;
import com.abinbev.ze.delivery.utils.StoreUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class StoreControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StoreService service;

    @InjectMocks
    private StoreController storeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.storeController)
                .build();
    }

    @Test
    public void getStoreByIdController_whenValidStoreId_thenReturnValidStore() throws Exception {
        given(service.getById(StoreUtils.STORE_VALID_ID))
                .willReturn(StoreUtils.createValidMockStore());

        mockMvc.perform(get(StoreController.BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tradingName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tradingName").value(StoreUtils.STORE_TRADING_NAME));

    }

    @Test
    public void getStoreByIdController_whenInvalidStoreId_thenReturnStoreNotFoundException() throws Exception {
        given(service.getById(StoreUtils.STORE_INVALID_ID))
                .willThrow(new StoreNotFoundException());

        mockMvc.perform(get(StoreController.BASE_URL + "/" + StoreUtils.STORE_INVALID_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createStoreController_whenValidStore_thenReturnValidStore() throws Exception {
        Store store = Mockito.mock(Store.class);

        given(service.create(store))
                .willReturn(StoreUtils.createValidNewMockStore());

        mockMvc.perform(
                post(StoreController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(StoreUtils.getValidMockStoreToString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void createStoreController_whenDuplicatedStore_thenReturnStoreDuplicatedException() throws Exception {
        given(service.create(any()))
                .willThrow(new StoreDuplicatedException());

        mockMvc.perform(
                post(StoreController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(StoreUtils.getDuplicatedMockStoreToString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void getAllNearStoresByLocation_whenSuccess_thenReturnStoreList() throws Exception {
        Point point = new Point(StoreUtils.STORE_LONGITUDE, StoreUtils.STORE_LATITUDE);

        given(service.searchNear(point))
                .willReturn(StoreUtils.createMockStoreList());

        mockMvc.perform(
                get(StoreController.BASE_URL)
                        .param(StoreUtils.STORE_PARAM_LONGITUDE, StoreUtils.STORE_LONGITUDE.toString())
                        .param(StoreUtils.STORE_PARAM_LATITUDE, StoreUtils.STORE_LATITUDE.toString()))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tradingName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tradingName").value(StoreUtils.STORE_TRADING_NAME))
                .andExpect(jsonPath("$[0].document").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].document").value(StoreUtils.STORE_VALID_DOCUMENT));
    }

}
