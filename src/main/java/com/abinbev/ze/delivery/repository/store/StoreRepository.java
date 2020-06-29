package com.abinbev.ze.delivery.repository.store;

import com.abinbev.ze.delivery.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends MongoRepository<Store, Long> {

    @Query("{ coverageArea: { $near: { $geometry: { type: ?0, coordinates: [?1, ?2] }, $maxDistance : ?3 } }}")
    List<Store> findByLocationNear(String type, Double x, Double y, Double distance);

}
