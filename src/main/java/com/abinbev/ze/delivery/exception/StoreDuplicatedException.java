package com.abinbev.ze.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Store duplicated.")
public class StoreDuplicatedException extends WebClientResponseException {

    private static final long serialVersionUID = 1L;

    public StoreDuplicatedException() {
        this("Store duplicated.");
    }

    public StoreDuplicatedException(String message) {
        super(HttpStatus.CONFLICT.value(), message, null, null, null);
    }

}
