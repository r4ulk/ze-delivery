package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.model.Store;

import com.abinbev.ze.delivery.utils.StoreUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataLoaderServiceTest {

    @Mock
    private DataLoaderService dataLoaderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStoresFromJSON_whenSuccess_thenReturnStoreList() {
        when(dataLoaderService.get())
                .thenReturn(StoreUtils.createMockStoreList());

        List<Store> stores = dataLoaderService.get();
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
        assertThat(stores.get(0).getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

}
