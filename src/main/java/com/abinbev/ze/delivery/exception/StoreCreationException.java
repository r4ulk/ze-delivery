package com.abinbev.ze.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class StoreCreationException extends WebClientResponseException {

    private static final long serialVersionUID = 1L;

    public StoreCreationException() {
        this("Couldn\"'\"t create the Store.");
    }

    public StoreCreationException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message, null, null, null);
    }

}
