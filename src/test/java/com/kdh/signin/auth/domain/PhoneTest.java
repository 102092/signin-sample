package com.kdh.signin.auth.domain;

import com.kdh.signin.auth.domain.Phone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author han
 */
class PhoneTest {


    @Test
    void createSuccess() {
        assertDoesNotThrow(() -> new Phone("010-1234-4567"));
    }

    @Test
    void createFail() {
        assertThrows(IllegalArgumentException.class, () -> new Phone("010-12412-4567"));
    }
}