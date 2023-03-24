package com.kdh.signin.auth.domain;


import lombok.EqualsAndHashCode;

import java.util.regex.Pattern;

/**
 * 전화번호
 * @author han
 */

@EqualsAndHashCode
public class Phone implements Identifier {

    public static final Phone NULL_OBJECT = new Phone("-1");

    private final String value;

    public Phone(String value) {
        this.value = value;
    }

    public static Phone of(String encryptedValue) {
        return new Phone(encryptedValue);
    }

    @Override
    public String getUniqueValue() {
        return this.value;
    }
}
