package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.application.port.out.VerifyPhonePort;
import org.springframework.stereotype.Service;

/**
 * @author han
 */

@Service
public class VerifyPhoneMockService implements VerifyPhonePort {

    @Override
    public boolean verify(String phoneNumber) {
        // mocking, always return true
        return true;
    }
}
