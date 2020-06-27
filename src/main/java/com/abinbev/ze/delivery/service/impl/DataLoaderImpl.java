package com.abinbev.ze.delivery.service.impl;

import com.abinbev.ze.delivery.exception.DataLoaderException;
import com.abinbev.ze.delivery.response.data.Json;
import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.service.DataLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author raul.klumpp
 * @implNote used to load pdvs (store) from the given json located at resources path: pdvs.json
 */
@Service
public class DataLoaderImpl implements DataLoader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Store> stores;

    private final ObjectMapper jsonMapper;

    @Autowired
    public DataLoaderImpl(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void load() throws DataLoaderException {
        InputStream inputStream = TypeReference.class.getResourceAsStream("/pdvs.json");
        try {
            Json json = jsonMapper.readValue(inputStream, Json.class);
            this.stores = json.getPdvs();
            logger.info("Stores data loaded from json... stores size: {}", this.stores.size());
        } catch (IOException e) {
            logger.error("Couldnt read stores from json'. Message: ", e.getMessage());
            throw new DataLoaderException(e.getMessage());
        }
    }

    @Override
    public List<Store> get() {
        return this.stores;
    }

}
