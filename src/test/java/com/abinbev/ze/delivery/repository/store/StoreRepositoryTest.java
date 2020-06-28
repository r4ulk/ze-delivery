package com.abinbev.ze.delivery.repository.store;

import com.abinbev.ze.delivery.model.Store;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository repository;

    private final String STORE_TRADING_NAME = "Adega Osasco";

    @Test
    public void shouldGetAllStores() {
        List<Store> stores = repository.findAll();
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(51);
        assertThat(stores.get(0).getTradingName()).isEqualTo(STORE_TRADING_NAME);
    }

    @Test
    public void shouldGetStoreById() {
        Optional<Store> store = repository.findById(1L);
        assertThat(store.get()).isNotNull();
        assertThat(store.get().getTradingName()).isEqualTo(STORE_TRADING_NAME);
    }

}
