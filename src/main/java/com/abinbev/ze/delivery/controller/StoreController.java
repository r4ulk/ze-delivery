package com.abinbev.ze.delivery.controller;

import com.abinbev.ze.delivery.model.Store;
import com.abinbev.ze.delivery.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/stores")
public class StoreController {

    private StoreService service;

    @GetMapping(value = "/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Store getStoreById(@PathVariable Long storeId) {
        return service.getById(storeId);
    }

    /**
     * Create Store
     * @param Store - The store (pdv) data
     * @return Store - Ther Store created
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Store create(@RequestBody Store store) {
        return service.create(store);
    }
}
