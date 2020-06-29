package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.exception.StoreNotFoundException;
import com.abinbev.ze.delivery.model.Store;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreServiceTest {

    @Autowired
    private StoreService service;

    private final String STORE_TRADING_NAME = "Adega Osasco";

    @Test
    public void getStoreById_whenValidStoreId_thenReturnValidStore() {
        Store store = service.getById(1L);
        assertThat(store).isNotNull();
        assertThat(store.getTradingName()).isEqualTo(STORE_TRADING_NAME);
    }

    @Test
    public void getStoreById_whenInvalidStoreId_thenReturnStoreNotFoundException() {
        assertThrows(StoreNotFoundException.class, () -> service.getById(171L));
    }


}
