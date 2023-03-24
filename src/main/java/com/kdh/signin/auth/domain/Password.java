package com.kdh.signin.auth.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author han
 */

@Getter
@EqualsAndHashCode
public class Password {

    private final String value;

    public Password(String password) {
        this.value = validate(password);
    }

    private String validate(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password can not be null or empty");
        }

        return password;
    }

    public boolean verified(Password password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can not be null");
        }

        return this.value.equals(password.value);
    }
}
