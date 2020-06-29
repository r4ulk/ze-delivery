package com.abinbev.ze.delivery;

import com.abinbev.ze.delivery.exception.DataLoaderException;
import com.abinbev.ze.delivery.exception.DataStoreException;
import com.abinbev.ze.delivery.service.DataLoaderService;
import com.abinbev.ze.delivery.service.DataStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	public void starter(DataLoaderService dataLoaderService, DataStoreService dataStoreService)
			throws DataLoaderException, DataStoreException {
		dataLoaderService.load();
		dataStoreService.clear();
		dataStoreService.store();
	}

}
