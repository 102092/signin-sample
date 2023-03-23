package com.kdh.signin.auth.domain;

import com.kdh.signin.common.CipherHelper;

import java.util.regex.Pattern;

/**
 * 전화번호
 * @author han
 */

public class Phone implements Identifier {

    private static final Pattern pattern = Pattern.compile("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");

    private final String encryptedValue;

    public Phone(String value) {
        this.encryptedValue = validate(value);
    }

    private String validate(String value) {
        if (!pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Wrong format of phone number.");
        }

        String encrypt = CipherHelper.encrypt(value);

        if (encrypt.isEmpty()) {
            throw new IllegalArgumentException("Encrypted phone number can not be empty");
        }

        return encrypt;
    }

    @Override
    public String getUniqueValue() {
        return this.encryptedValue;
    }
}
