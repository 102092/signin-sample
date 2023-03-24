package com.kdh.signin.auth.domain;

import lombok.Value;

/**
 * @author han
 */

@Value
public class Email implements Identifier {

    public static final Email NULL_OBJECT = new Email("null@null.com");

    String value;

    public Email(String value) {
        this.value = value;
    }

    @Override
    public String getUniqueValue() {
        return this.value;
    }
}
