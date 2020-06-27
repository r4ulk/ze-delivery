package com.abinbev.ze.delivery.service.impl;

import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.service.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderImpl implements DataLoader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Store> stores;

    @Override
    public void load() {

    }
}
