package com.kdh.signin.auth.domain;

import java.util.regex.Pattern;

/**
 * @author han
 */
public class Email implements Identifier{

    private final static Pattern pattern  = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private final String value;

    public Email(String value) {
        this.value = validate(value);
    }

    private String validate(String value) {
        if (pattern.matcher(value).matches()) {
            return value;
        }
        throw new IllegalArgumentException("Email seems not valid");
    }

    @Override
    public String getUniqueValue() {
        return this.value;
    }
}
