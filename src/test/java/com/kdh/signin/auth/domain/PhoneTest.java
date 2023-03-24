package com.kdh.signin.auth.domain;

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
}