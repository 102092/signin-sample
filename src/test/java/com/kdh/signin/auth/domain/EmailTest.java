package com.kdh.signin.auth.domain;

import com.kdh.signin.auth.domain.Email;
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

    @Test
    void throwIfNotValid() {
        assertThrows(IllegalArgumentException.class, () -> new Email("test@gmail"));
    }
}