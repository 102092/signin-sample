package com.kdh.signin.auth.adapter.out.web;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class SigninResponse {

    private final String token;

    public SigninResponse(String token) {this.token = token;}
}
