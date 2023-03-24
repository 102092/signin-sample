package com.kdh.signin.auth.domain;

import lombok.Value;

/**
 * @author han
 */

@Value
public class Name {

    String value;

    public Name(String value) {
        this.value = value;
    }
}
