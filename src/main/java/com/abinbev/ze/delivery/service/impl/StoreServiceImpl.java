package com.abinbev.ze.delivery.service.impl;

import com.abinbev.ze.delivery.exception.StoreCreationException;
import com.abinbev.ze.delivery.exception.StoreDuplicatedException;
import com.abinbev.ze.delivery.exception.StoreNotFoundException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.repository.store.StoreRepository;
import com.abinbev.ze.delivery.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repository;

    @Value("${store.notfound}")
    private String MESSAGE_STORE_NOTFOUND;

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
    public List<StoreService> searchNear(Point point) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
