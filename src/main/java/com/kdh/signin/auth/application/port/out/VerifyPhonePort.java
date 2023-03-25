package com.kdh.signin.auth.application.port.out;

/**
 * @author han
 */
public interface VerifyPhonePort {

    /**
     * @param phoneNumber
     * @return verified token of phoneNumber from external service
     */
    String verify(String phoneNumber);

    /**
     * @param token from external service
     * @return boolean if token is valid
     */
    boolean verifyToken(String token);
}
