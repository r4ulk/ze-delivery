package com.abinbev.ze.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No store found")
public class StoreNotFoundException extends WebClientResponseException {

    private static final long serialVersionUID = 1L;

    public StoreNotFoundException() {
        this("Store not found.");
    }

    public StoreNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message, null, null, null);
    }

}
