package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.domain.Phone;
import org.springframework.stereotype.Service;

/**
 * @author han
 */

@Service
public class VerifyPhoneMockService {

    public boolean verity(Phone phone) {
        // mock verify

        return true;
    }
}
