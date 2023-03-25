package com.kdh.signin.auth.domain;


import com.kdh.signin.common.CipherHelper;
import lombok.Value;


/**
 * 전화번호
 * @author han
 */

@Value
public class Phone implements Identifier {

    public static final Phone NULL_OBJECT = new Phone("-1");

    String value;

    public Phone(String value) {
        this.value = validate(value);
    }

    private String validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Phone can not be null or empty");
        }
        return value;
    }

    public static Phone decryptFrom(String encryptedValue) {
        String decrypt = CipherHelper.decrypt(encryptedValue);
        if (decrypt.isEmpty()) {
            throw new IllegalArgumentException("phone decrypt string should not be empty");
        }
        return new Phone(decrypt);
    }

    @Override
    public String getUniqueValue() {
        return this.value;
    }

    public String getEncryptValue() {
        return CipherHelper.encrypt(this.value);
    }
}
