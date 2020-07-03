package com.abinbev.ze.delivery.repository.store;

import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.utils.GeoJsonTypeEnum;
import com.abinbev.ze.delivery.utils.StoreUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreRepositoryIntegrationTest {

    @Autowired
    private StoreRepository repository;

    @Test
    public void getAllStoresFromDB_whenSuccess_thenReturnStoreList() {
        List<Store> stores = repository.findAll();
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(48);
        assertThat(stores.get(0).getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

    @Test
    public void getStoreById_whenValidStoreId_thenReturnValidStore() {
        Optional<Store> store = repository.findById(StoreUtils.STORE_VALID_ID);
        assertThat(store.get()).isNotNull();
        assertThat(store.get().getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

    @Test
    public void getAllNearStoresByLocation_whenSuccess_thenReturnStoreList() {
        Distance distance = new Distance(0.1, Metrics.KILOMETERS); // 100 meters

        List<Store> stores = repository.findByLocationNear(GeoJsonTypeEnum.MULTIPOLYGON.getValue(),
                StoreUtils.STORE_LONGITUDE, StoreUtils.STORE_LATITUDE, distance.getValue());

        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
        assertThat(stores.get(0).getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

}
