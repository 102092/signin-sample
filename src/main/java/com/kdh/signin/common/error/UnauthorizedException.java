package com.kdh.signin.common.error;

/**
 * @author han
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String s) {
        super(s);
    }
}
