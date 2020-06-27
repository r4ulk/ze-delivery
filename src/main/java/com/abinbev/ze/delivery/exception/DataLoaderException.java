package com.abinbev.ze.delivery.exception;

public class DataLoaderException extends Exception {

    public DataLoaderException() {
    }

    public DataLoaderException(String message) {
        super(message);
    }

    public DataLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataLoaderException(Throwable cause) {
        super(cause);
    }

    public DataLoaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
