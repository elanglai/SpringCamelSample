package com.trp.mifid.exception;

abstract public class AppException extends RuntimeException {
    public AppException(String msg) {
        super(msg);
    }
}
