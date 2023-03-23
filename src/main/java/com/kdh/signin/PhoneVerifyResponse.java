package com.kdh.signin;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class PhoneVerifyResponse {

    private final String token;

    private final String phoneNumber;

    public PhoneVerifyResponse(String token, String phoneNumber) {
        this.token = token;
        this.phoneNumber = phoneNumber;
    }
}
