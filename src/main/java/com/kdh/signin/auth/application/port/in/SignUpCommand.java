package com.kdh.signin.auth.application.port.in;

import com.kdh.signin.auth.domain.*;
import lombok.Builder;
import lombok.Getter;

/**
 * @author han
 */

@Getter
public class SignUpCommand {
    private Email email;

    private NickName nickName;

    private Password password;

    private Name name;

    private Phone phone;

    private String token;

    @Builder
    protected SignUpCommand(Email email, NickName nickName, Password password, Name name, Phone phone, String token) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.token = token;
    }
}
