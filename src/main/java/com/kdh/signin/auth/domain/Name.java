package com.kdh.signin.auth.domain;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class Name {

    private final String value;

    public Name(String value) {
        this.value = value;
    }
}
