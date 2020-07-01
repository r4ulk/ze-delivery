package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.exception.StoreDuplicatedException;
import com.abinbev.ze.delivery.exception.StoreNotFoundException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.utils.GeoJsonTypeEnum;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreServiceTest {

    @Autowired
    private StoreService service;

    @Autowired
    private DataLoaderService dataLoaderService;

    private final String STORE_TRADING_NAME = "Adega Osasco";

    private final String STORE_VALID_NEW_DOCUMENT = "1432132123891/0007";

    private final Long STORE_VALID_NEW_ID = 6666666L;

    @BeforeEach
    public void setup() {
        service.deleteById(STORE_VALID_NEW_ID);
    }

    @AfterEach
    public void finalizer() {
        service.deleteById(STORE_VALID_NEW_ID);
    }

    @Test
    public void getStoreById_whenValidStoreId_thenReturnValidStore() throws IOException {
        Store store = service.getById(1L);
        assertThat(store).isNotNull();
        assertThat(store.getTradingName()).isEqualTo(STORE_TRADING_NAME);
    }

    @Test
    public void getStoreById_whenInvalidStoreId_thenReturnStoreNotFoundException() {
        assertThrows(StoreNotFoundException.class, () -> service.getById(171L));
    }

    @Test
    public void createStore_whenSuccess_thenReturnStoreCreated() throws IOException {
        Store store = dataLoaderService.get().get(0); // gets the first Store loaded from json
        assertThat(store).isNotNull();

        // change unique values
        store.setId(STORE_VALID_NEW_ID);
        store.setDocument(STORE_VALID_NEW_DOCUMENT);

        Store created = service.create(store);
        assertThat(created).isNotNull();
        assertThat(store.getId()).isEqualTo(STORE_VALID_NEW_ID);
        assertThat(store.getDocument()).isEqualTo(STORE_VALID_NEW_DOCUMENT);
    }

    @Test
    public void createStore_whenDuplicatedCNPJ_thenReturnStoreDuplicatedException() {
        Store store = dataLoaderService.get().get(0); // gets the first Store loaded from json
        assertThat(store).isNotNull();

        store.setId(666);

        assertThrows(StoreDuplicatedException.class, () -> service.create(store));
    }

    @Test
    public void getAllNearStoresByLocation_whenSuccess_thenReturnStoreList() {
        List<Store> stores = service.searchNear(new Point(-43.297337, -23.013538));

        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
        assertThat(stores.get(0).getTradingName()).isEqualTo(STORE_TRADING_NAME);
    }

}
