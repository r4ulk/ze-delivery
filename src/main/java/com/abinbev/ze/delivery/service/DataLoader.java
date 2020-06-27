package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.exception.DataLoaderException;
import com.abinbev.ze.delivery.model.Store;

import java.util.List;

public interface DataLoader {
    void load() throws DataLoaderException;
    List<Store> get();
}
