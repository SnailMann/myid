package com.snailmann.myid.common.exception;

/**
 * @author liwenjie
 */
public class TagNoRegisterException extends RuntimeException {

    public TagNoRegisterException() {
    }

    public TagNoRegisterException(String message) {
        super(message);
    }

    public TagNoRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNoRegisterException(Throwable cause) {
        super(cause);
    }

    public TagNoRegisterException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
