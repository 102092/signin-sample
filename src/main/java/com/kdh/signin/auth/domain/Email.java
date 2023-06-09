package com.kdh.signin.auth.domain;

import lombok.Value;

import java.util.regex.Pattern;

/**
 * @author han
 */

@Value
public class Email implements Identifier {

    public static final Email NULL_OBJECT = new Email("null@null.com");

    private static final Pattern patten = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        return new Email(validate(value));
    }

    private static String validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Email can not be null or empty");
        }

        if (!patten.matcher(value).matches()) {
            throw new IllegalArgumentException("This value is not proper for email");
        }

        return value;
    }

    @Override
    public String getUniqueValue() {
        return this.value;
    }
}
