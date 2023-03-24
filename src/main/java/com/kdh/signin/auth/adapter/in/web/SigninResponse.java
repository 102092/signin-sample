package com.kdh.signin.auth.adapter.in.web;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class SigninResponse {

    private final String token;

    public SigninResponse(String token) {this.token = token;}
}
