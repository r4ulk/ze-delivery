package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.utils.StoreUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataLoaderServiceIntegrationTest {

    @Autowired
    private DataLoaderService dataLoaderService;

    @Test
    public void getAllStoresFromJSON_whenSuccess_thenReturnStoreList() {
        List<Store> stores = dataLoaderService.get();
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(51);
        assertThat(stores.get(0).getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

}
