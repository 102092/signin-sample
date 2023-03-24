package com.kdh.signin.auth.adapter.in.web;

import com.kdh.signin.auth.domain.Email;
import com.kdh.signin.auth.domain.Name;
import com.kdh.signin.auth.domain.NickName;
import com.kdh.signin.auth.domain.Phone;
import lombok.Builder;
import lombok.Getter;

/**
 * @author han
 */

@Getter
public class UserInfoResponse {

    private final String email;

    private final String nickName;

    private final String name;

    private final String phoneNumber;


    @Builder
    protected UserInfoResponse(Email email, NickName nickName, Name name, Phone phoneNumber) {
        this.email = email.getUniqueValue();
        this.nickName = nickName.getValue();
        this.name = name.getValue();
        this.phoneNumber = phoneNumber.getUniqueValue();
    }
}
