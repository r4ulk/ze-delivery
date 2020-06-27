package com.abinbev.ze.delivery.repository.store;

import com.abinbev.ze.delivery.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {

    @Query("{'_id': ?0}")
    Store get(long storeId);

}
