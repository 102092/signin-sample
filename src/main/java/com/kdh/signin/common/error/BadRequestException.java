package com.kdh.signin.common.error;

/**
 * @author han
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String s) {
        super(s);
    }
}
