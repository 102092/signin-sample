package com.kdh.signin.auth.application.port.out;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class PhoneVerifyResponse {

    private final boolean verified;

    private final String phoneNumber;

    public PhoneVerifyResponse(boolean verified, String phoneNumber) {
        this.verified = verified;
        this.phoneNumber = phoneNumber;
    }
}
