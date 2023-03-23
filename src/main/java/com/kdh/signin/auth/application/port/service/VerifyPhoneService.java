package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.domain.Phone;
import com.kdh.signin.common.CipherHelper;
import org.springframework.stereotype.Service;

/**
 * @author han
 */

@Service
public class VerifyPhoneService {

    public boolean verity(Phone phone) {
        // mock verify

        return true;
    }
}
