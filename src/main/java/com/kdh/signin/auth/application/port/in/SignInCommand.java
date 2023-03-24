package com.kdh.signin.auth.application.port.in;

import com.kdh.signin.auth.domain.*;
import lombok.Builder;
import lombok.Getter;

/**
 * @author han
 */

@Getter
public class SignInCommand {
    private Email email;

    private Phone phone;

    private Password password;

    @Builder
    protected SignInCommand(Email email,  Password password, Phone phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
