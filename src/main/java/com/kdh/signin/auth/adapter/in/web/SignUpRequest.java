package com.kdh.signin.auth.adapter.in.web;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class SignUpRequest {

    private String email;
    private String nickName;
    private String password;
    private String name;
    private String phoneNumber;

    public SignUpRequest(String email, String nickName, String password, String name, String phoneNumber) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
