package com.kdh.signin.auth.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author han
 */
class EmailTest {


    @Test
    void createSuccess() {
        assertDoesNotThrow(() -> Email.of("test@gmail.com"));
    }

    @Test
    void throwIfNotValid() {
        assertThrows(IllegalArgumentException.class, () -> Email.of("test"));
    }
}