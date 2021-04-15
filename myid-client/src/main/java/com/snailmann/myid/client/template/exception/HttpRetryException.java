package com.snailmann.myid.client.template.exception;

public class HttpRetryException extends RuntimeException {

    public HttpRetryException() {
    }

    public HttpRetryException(String message) {
        super(message);
    }

    public HttpRetryException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRetryException(Throwable cause) {
        super(cause);
    }

    public HttpRetryException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}