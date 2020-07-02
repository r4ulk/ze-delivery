package com.abinbev.ze.delivery.service.impl;

import com.abinbev.ze.delivery.exception.StoreCreationException;
import com.abinbev.ze.delivery.exception.StoreDuplicatedException;
import com.abinbev.ze.delivery.exception.StoreNotFoundException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.repository.store.StoreRepository;
import com.abinbev.ze.delivery.service.StoreService;
import com.abinbev.ze.delivery.utils.GeoJsonTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import org.springframework.data.geo.Point;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repository;

    @Value("${store.notfound}")
    private String MESSAGE_STORE_NOTFOUND;

    @Value("${store.distance.range}")
    private double STORE_RANGE_KILOMETERS;

    @Autowired
    public StoreServiceImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Store create(Store s) {
        try {
            return Optional.of(repository.insert(s))
                    .orElseThrow(() -> new StoreCreationException());
        }catch (DuplicateKeyException ex) {
            throw new StoreDuplicatedException();
        }
    }

    @Override
    public Store getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException(MESSAGE_STORE_NOTFOUND));
    }

    @Override
    public List<Store> searchNear(Point point) {
        Distance distance = new Distance(STORE_RANGE_KILOMETERS, Metrics.KILOMETERS);
        return repository.findByLocationNear(
                GeoJsonTypeEnum.MULTIPOLYGON.getValue(),
                point.getX(),
                point.getY(),
                distance.getValue());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
