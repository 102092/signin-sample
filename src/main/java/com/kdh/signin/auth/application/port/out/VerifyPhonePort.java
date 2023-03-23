package com.kdh.signin.auth.application.port.out;

/**
 * @author han
 */
public interface VerifyPhonePort {

    boolean verify(String phoneNumber);
}
