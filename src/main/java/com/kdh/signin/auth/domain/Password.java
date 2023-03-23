package com.kdh.signin.auth.domain;

import com.kdh.signin.common.CipherHelper;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author han
 */

@Getter
@EqualsAndHashCode
public class Password {

    private final String encryptedValue;

    public Password(String password) {
        this.encryptedValue = validate(password);
    }

    private String validate(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password can not be null or empty");
        }

        String encrypt = CipherHelper.encrypt(password);

        if (encrypt.isEmpty()) {
            throw new IllegalArgumentException("Encrypt string can not be empty");
        }

        return encrypt;
    }

    public boolean verified(Password password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can not be null");
        }

        return this.encryptedValue.equals(password.encryptedValue);
    }
}
