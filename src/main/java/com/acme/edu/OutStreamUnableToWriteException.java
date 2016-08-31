package com.acme.edu;

/**
 * Created by Java_5 on 29.08.2016.
 */
public class OutStreamUnableToWriteException extends Exception {
    public OutStreamUnableToWriteException() {
    }

    public OutStreamUnableToWriteException(String message) {
        super(message);
    }

    public OutStreamUnableToWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutStreamUnableToWriteException(Throwable cause) {
        super(cause);
    }

    public OutStreamUnableToWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
