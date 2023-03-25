package com.kdh.signin.auth.adapter.out.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author han
 */

@Getter
@NoArgsConstructor
public class SigninResponse {

    private String token;

    public SigninResponse(String token) {this.token = token;}
}
