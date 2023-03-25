package com.kdh.signin.auth.adapter.out.web;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class SignUpResponse {

    private final String id;

    public SignUpResponse(Long id) {this.id = String.valueOf(id);}
}
