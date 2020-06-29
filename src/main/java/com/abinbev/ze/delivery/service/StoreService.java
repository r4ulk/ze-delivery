package com.abinbev.ze.delivery.service;

import com.abinbev.ze.delivery.model.Store;

import java.awt.*;
import java.util.List;

public interface StoreService {
    Store create(Store store);
    Store getById(Long id);
    List<StoreService> searchNear(Point point);
}
