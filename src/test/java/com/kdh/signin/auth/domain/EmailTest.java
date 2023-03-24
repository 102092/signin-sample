package com.kdh.signin.auth.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author han
 */
class EmailTest {


    @Test
    void createSuccess() {
        assertDoesNotThrow(() -> new Email("test@gmail.com"));
    }

}