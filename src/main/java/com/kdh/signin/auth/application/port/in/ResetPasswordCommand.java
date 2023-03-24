package com.kdh.signin.auth.application.port.in;

import com.kdh.signin.auth.domain.Password;
import com.kdh.signin.auth.domain.Phone;
import lombok.Getter;

/**
 * @author han
 */

@Getter
public class ResetPasswordCommand {

    private final Phone phone;

    private final Password passwordForRest;

    public ResetPasswordCommand(Phone phone, Password passwordForRest) {
        this.phone = phone;
        this.passwordForRest = passwordForRest;
    }
}
