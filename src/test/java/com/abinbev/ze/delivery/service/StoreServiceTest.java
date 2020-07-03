package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.exception.StoreDuplicatedException;
import com.abinbev.ze.delivery.exception.StoreNotFoundException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.utils.StoreUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.geo.Point;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    private StoreService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStoreById_whenValidStoreId_thenReturnValidStore() {
        when(service.getById(StoreUtils.STORE_VALID_ID))
                .thenReturn(StoreUtils.createValidMockStore());

        Store store = service.getById(StoreUtils.STORE_VALID_ID);
        assertThat(store).isNotNull();
        assertThat(store.getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

    @Test
    public void getStoreById_whenInvalidStoreId_thenReturnStoreNotFoundException() {
        when(service.getById(StoreUtils.STORE_INVALID_ID))
                .thenThrow(new StoreNotFoundException());

        assertThrows(StoreNotFoundException.class,
                () -> service.getById(StoreUtils.STORE_INVALID_ID));
    }

    @Test
    public void createStore_whenSuccess_thenReturnStoreCreated() {
        Store store = Mockito.mock(Store.class);

        when(service.create(store))
                .thenReturn(StoreUtils.createValidNewMockStore());

        Store created = service.create(store);
        assertThat(created).isNotNull();
        assertThat(created.getId()).isEqualTo(StoreUtils.STORE_VALID_NEW_ID);
        assertThat(created.getDocument()).isEqualTo(StoreUtils.STORE_VALID_NEW_DOCUMENT);
    }

    @Test
    public void createStore_whenDuplicatedCNPJ_thenReturnStoreDuplicatedException() {
        Store store = Mockito.mock(Store.class);

        when(service.create(store))
                .thenThrow(new StoreDuplicatedException());

        assertThrows(StoreDuplicatedException.class,
                () -> service.create(store));
    }

    @Test
    public void getAllNearStoresByLocation_whenSuccess_thenReturnStoreList() {
        Point point = new Point(StoreUtils.STORE_LONGITUDE, StoreUtils.STORE_LATITUDE);
        when(service.searchNear(point))
                .thenReturn(StoreUtils.createMockStoreList());

        List<Store> stores = service.searchNear(point);
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
        assertThat(stores.get(0).getTradingName()).isEqualTo(StoreUtils.STORE_TRADING_NAME);
    }

}
