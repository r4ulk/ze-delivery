package com.abinbev.ze.delivery.service.impl;

import com.abinbev.ze.delivery.exception.DataStoreException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.repository.store.StoreRepository;
import com.abinbev.ze.delivery.service.DataLoader;
import com.abinbev.ze.delivery.service.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author raul.klumpp
 * @implNote service used to store data and clear data from mongodb repository
 */
@Service
public class DataStoreImpl implements DataStore {

    private StoreRepository repository;

    private DataLoader dataLoader;

    @Autowired
    public DataStoreImpl(StoreRepository repository, DataLoader dataLoader) {
        this.repository = repository;
        this.dataLoader = dataLoader;
    }

    @Override
    public void clear() throws DataStoreException {
        repository.deleteAll();
    }

    @Override
    public List<Store> store() throws DataStoreException {
        return repository.saveAll(dataLoader.get());
    }

}
