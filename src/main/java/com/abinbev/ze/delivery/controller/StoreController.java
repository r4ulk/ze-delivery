package com.abinbev.ze.delivery.controller;

import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author raul.klumpp
 * @apiNote The Store (PDV) API RESTful endpoint
 */
@RestController
@AllArgsConstructor
@RequestMapping(StoreController.BASE_URL)
public class StoreController {

    public static final String BASE_URL = "/stores";

    private StoreService service;

    /**
     * Retrieve Store by given storeId
     * @param storeId - The store id
     * @return Store - The Store retrieved
     */
    @GetMapping(value = "/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Store getStoreById(@PathVariable Long storeId) {
        return service.getById(storeId);
    }

    /**
     * Create Store
     * @param store - The store (pdv) data
     * @return Store - The Store created
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Store create(@RequestBody Store store) {
        return service.create(store);
    }

    /**
     * Retrieve an Store List near a given Location ( lng, lat )
     * @param lng - The longitude
     * @param lat - The latitude
     * @return Store List - The Store List created
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Store> getByNearLocation(@RequestParam Double lng, @RequestParam Double lat) {
        return service.searchNear(new Point(lng, lat));
    }
}
