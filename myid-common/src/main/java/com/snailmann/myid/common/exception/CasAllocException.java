package com.snailmann.myid.common.exception;

public class CasAllocException extends RuntimeException {

    public CasAllocException() {
    }

    public CasAllocException(String message) {
        super(message);
    }

    public CasAllocException(String message, Throwable cause) {
        super(message, cause);
    }

    public CasAllocException(Throwable cause) {
        super(cause);
    }

    public CasAllocException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}