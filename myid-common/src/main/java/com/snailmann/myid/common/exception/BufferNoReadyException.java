package com.snailmann.myid.common.exception;

public class BufferNoReadyException extends RuntimeException {

    public BufferNoReadyException() {
    }

    public BufferNoReadyException(String message) {
        super(message);
    }

    public BufferNoReadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BufferNoReadyException(Throwable cause) {
        super(cause);
    }

    public BufferNoReadyException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}