package com.kdh.signin.auth.domain;

import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

/**
 * @author han
 */

@EqualsAndHashCode
public class Email implements Identifier {

    public static final Email NULL_OBJECT = new Email("null@null.com");

    private final String value;

    public Email(String value) {
        this.value = value;
    }

    @Override
    public String getUniqueValue() {
        return this.value;
    }
}
