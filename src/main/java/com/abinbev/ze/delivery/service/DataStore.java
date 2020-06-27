package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.exception.DataStoreException;
import com.abinbev.ze.delivery.model.Store;

import java.util.List;

public interface DataStore {
    void clear() throws DataStoreException;
    List<Store> store() throws DataStoreException;
}
