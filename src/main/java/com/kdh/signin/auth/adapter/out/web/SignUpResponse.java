package com.kdh.signin.auth.adapter.out.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author han
 */

@Getter
@NoArgsConstructor
public class SignUpResponse {

    private String id;

    public SignUpResponse(Long id) {this.id = String.valueOf(id);}
}
