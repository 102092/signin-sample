package com.kdh.signin.auth.domain;


import com.kdh.signin.common.CipherHelper;
import lombok.EqualsAndHashCode;
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
        this.value = value;
    }

    public static Phone of(String encryptedValue) {
        return new Phone(encryptedValue);
    }

    @Override
    public String getUniqueValue() {
        return this.value;
    }

    public String getEncryptValue() {
        return CipherHelper.encrypt(this.value);
    }
}
