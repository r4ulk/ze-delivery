package com.abinbev.ze.delivery.repository.store;

import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.utils.GeoJsonTypeEnum;
import com.abinbev.ze.delivery.utils.StoreUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoreRepositoryTest {

    @Mock
    private StoreRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStoresFromDB_whenSuccess_thenReturnStoreList() {
        when(repository.findAll())
                .thenReturn(StoreUtils.createMockStoreList());

        List<Store> stores = repository.findAll();
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
        assertThat(stores.get(0).getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

    @Test
    public void getStoreById_whenValidStoreId_thenReturnValidStore() {
        when(repository.findById(StoreUtils.STORE_VALID_ID))
                .thenReturn(Optional.of(StoreUtils.createValidMockStore()));

        Optional<Store> store = repository.findById(StoreUtils.STORE_VALID_ID);
        assertThat(store.get()).isNotNull();
        assertThat(store.get().getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

    @Test
    public void getAllNearStoresByLocation_whenSuccess_thenReturnStoreList() {
        Distance distance = new Distance(0.1, Metrics.KILOMETERS); // 100 meters

        when(repository.findByLocationNear(GeoJsonTypeEnum.MULTIPOLYGON.getValue(),
                -43.297337,
                -23.013538,
                distance.getValue()))
                .thenReturn(StoreUtils.createMockStoreList());

        List<Store> stores = repository.findByLocationNear(GeoJsonTypeEnum.MULTIPOLYGON.getValue(),
                -43.297337,
                -23.013538,
                distance.getValue());

        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
        assertThat(stores.get(0).getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

}
