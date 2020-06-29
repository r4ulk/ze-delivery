package com.abinbev.ze.delivery.repository.store;

import com.abinbev.ze.delivery.model.Store;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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

    @Test
    public void shouldGetAllNearStoresByLocation() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(43.297337,-23.013538));
        List<GeoJsonPolygon> polygons = new ArrayList<>();
        polygons.add(new GeoJsonPolygon(points));
        GeoJsonMultiPolygon geoJsonMultiPolygon = new GeoJsonMultiPolygon(polygons);

        Distance distance = new Distance(0.1, Metrics.KILOMETERS); // 100 meters
        List<Store> stores = repository.findByLocationNear(geoJsonMultiPolygon.getType(), point.getX(), point.getY(), distance.getValue());

        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
        assertThat(stores.get(0).getTradingName()).isEqualTo(STORE_TRADING_NAME);
    }

}
