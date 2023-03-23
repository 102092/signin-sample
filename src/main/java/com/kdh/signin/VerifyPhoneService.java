package com.kdh.signin;

import org.springframework.stereotype.Service;

/**
 * @author han
 */

@Service
public class VerifyPhoneService {

    public String verity(Phone phone) {
        // mock verify

        return CipherHelper.encrypt(phone.getValue());
    }
}
