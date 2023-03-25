package com.kdh.signin.auth.adapter.out.web;

import com.kdh.signin.auth.domain.Email;
import com.kdh.signin.auth.domain.Name;
import com.kdh.signin.auth.domain.NickName;
import com.kdh.signin.auth.domain.Phone;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author han
 */

@Getter
@NoArgsConstructor
public class UserInfoResponse {

    private String email;

    private String nickName;

    private String name;

    private String phoneNumber;

    @Builder
    protected UserInfoResponse(Email email, NickName nickName, Name name, Phone phoneNumber) {
        this.email = email.getUniqueValue();
        this.nickName = nickName.getValue();
        this.name = name.getValue();
        this.phoneNumber = phoneNumber.getUniqueValue();
    }
}
