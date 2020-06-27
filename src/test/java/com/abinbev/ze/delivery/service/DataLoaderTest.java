package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.model.Store;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataLoaderTest {

    @Autowired
    DataLoader dataLoader;

    @Test
    public void shouldGetLoadedStores() throws Exception {
        List<Store> stores = dataLoader.get();
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(51);
        assertThat(stores.get(0).getTradingName()).isEqualTo("Adega Osasco");
    }

}
