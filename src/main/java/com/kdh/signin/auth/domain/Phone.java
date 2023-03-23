package com.kdh.signin.auth.domain;

import lombok.Getter;

import java.util.regex.Pattern;

/**
 * 전화번호
 * @author han
 */

@Getter
public class Phone {

    private static final Pattern pattern = Pattern.compile("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");

    private final String value;

    public Phone(String value) {
        this.value = validate(value);
    }

    private String validate(String value) {
        if (pattern.matcher(value).matches()) {
            return value;
        }

        throw new IllegalArgumentException("Wrong format of phone number.");
    }
}
