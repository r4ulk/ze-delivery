package com.abinbev.ze.delivery.service.impl;

import com.abinbev.ze.delivery.exception.DataStoreException;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.repository.store.StoreRepository;
import com.abinbev.ze.delivery.service.DataLoaderService;
import com.abinbev.ze.delivery.service.DataStoreService;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author raul.klumpp
 * @implNote service used to store data and clear data from mongodb repository
 */
@Service
public class DataStoreServiceImpl implements DataStoreService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StoreRepository repository;

    private DataLoaderService dataLoaderService;

    @Autowired
    public DataStoreServiceImpl(StoreRepository repository, DataLoaderService dataLoaderService) {
        this.repository = repository;
        this.dataLoaderService = dataLoaderService;
    }

    @Override
    public void clear() throws DataStoreException {
        try {
            repository.deleteAll();
        } catch (MongoException e) {
            throw new DataStoreException(e.getMessage());
        }
    }

    @Override
    public List<Store> store()  {
        return dataLoaderService.get().stream()
                .filter(s -> {
                    try {
                        repository.save(s);
                        return Boolean.TRUE;
                    } catch (Exception e) {
                        logger.warn("MongoDB Warning - {}", e.getMessage());
                    }
                    return Boolean.FALSE;
                }).collect(Collectors.toList());
    }

}
