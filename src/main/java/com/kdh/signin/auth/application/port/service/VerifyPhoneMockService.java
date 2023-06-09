package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.application.port.out.VerifyPhonePort;
import org.springframework.stereotype.Service;

/**
 * @author han
 */

@Service
public class VerifyPhoneMockService implements VerifyPhonePort {

    @Override
    public String verify(String phoneNumber) {
        // mocking
        return "verified";
    }

    @Override
    public boolean verifyToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        return true;
    }
}
