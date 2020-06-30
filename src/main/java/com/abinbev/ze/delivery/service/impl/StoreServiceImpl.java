package com.abinbev.ze.delivery.service.impl;

import com.abinbev.ze.delivery.exception.StoreCreationException;
import com.abinbev.ze.delivery.exception.StoreNotFoundException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.repository.store.StoreRepository;
import com.abinbev.ze.delivery.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repository;

    @Autowired
    public StoreServiceImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Store create(Store s) {
        return Optional.of(repository.save(s))
                .orElseThrow(() -> new StoreCreationException());
    }

    @Override
    public Store getById(Long id) {
        Store store = repository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException());
        return store;
    }

    @Override
    public List<StoreService> searchNear(Point point) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
