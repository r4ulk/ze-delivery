package com.abinbev.ze.delivery;

import com.abinbev.ze.delivery.service.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	public void starter(DataLoader dataLoader) throws Exception {
		dataLoader.load();
	}

}
