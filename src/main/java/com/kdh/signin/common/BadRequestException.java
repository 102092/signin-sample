package com.kdh.signin.common;

/**
 * @author han
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String s) {
        super(s);
    }
}
