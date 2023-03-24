package com.kdh.signin.auth.domain;

import com.kdh.signin.common.CipherHelper;
import lombok.Value;

/**
 * @author han
 */

@Value
public class Password {

    String value;

    public Password(String password) {
        this.value = validate(password);
    }

    public static Password decryptFrom(String encryptPassword) {
        return new Password(CipherHelper.decrypt(encryptPassword));
    }

    private String validate(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password can not be null or empty");
        }

        return password;
    }

    public boolean isNotSame(Password password) {
        return !isSame(password);
    }

    public boolean isSame(Password password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can not be null");
        }

        return this.value.equals(password.value);
    }

    public String getEncryptValue() {
        return CipherHelper.encrypt(this.value);
    }
}
