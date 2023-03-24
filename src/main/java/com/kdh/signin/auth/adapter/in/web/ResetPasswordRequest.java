package com.kdh.signin.auth.adapter.in.web;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class ResetPasswordRequest {

    private final String phoneNumber;

    private final String token;

    private final String passwordForRest;

    public ResetPasswordRequest(String phoneNumber, String token, String passwordForRest) {
        this.phoneNumber = phoneNumber;
        this.token = token;
        this.passwordForRest = passwordForRest;
    }
}
