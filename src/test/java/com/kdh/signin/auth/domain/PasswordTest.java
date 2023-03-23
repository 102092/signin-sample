package com.kdh.signin.auth.domain;

import com.kdh.signin.common.CipherHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author han
 */
class PasswordTest {

    @Test
    void create() {
        assertDoesNotThrow(() -> new Password("test"));
    }

    @Test
    void encryptAndDecrypt() {
        //given
        Password test = new Password("test");

        //when
        String decrypt = CipherHelper.decrypt(test.getEncryptedValue());

        //then
        assertEquals("test", decrypt);
    }

}