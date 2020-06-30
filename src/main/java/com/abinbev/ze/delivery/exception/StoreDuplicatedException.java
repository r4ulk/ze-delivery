package com.abinbev.ze.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class StoreDuplicatedException extends WebClientResponseException {

    private static final long serialVersionUID = 1L;

    public StoreDuplicatedException() {
        this("Store duplicated.");
    }

    public StoreDuplicatedException(String message) {
        super(HttpStatus.CONFLICT.value(), message, null, null, null);
    }

}
