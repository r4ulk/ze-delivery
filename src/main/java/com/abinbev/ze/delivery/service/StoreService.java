package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.model.Store;
import org.springframework.data.geo.Point;

import java.util.List;

public interface StoreService {
    Store create(Store store);
    Store getById(Long id);
    List<Store> searchNear(Point point);
    void deleteById(Long id);
}
