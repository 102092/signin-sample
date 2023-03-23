package com.kdh.signin.auth.adapter.out.persistence;

import com.kdh.signin.auth.domain.*;
import org.springframework.stereotype.Component;

/**
 * @author han
 */


@Component
class AccountMapper {

    AccountJpaEntity mapToEntity(Email email, NickName nickName, Password password, Name name, Phone phone) {
        return AccountJpaEntity.builder()
            .email(email.getUniqueValue())
            .nickName(nickName.getValue())
            .password(password.getEncryptedValue())
            .name(name.getValue())
            .phoneNumber(phone.getUniqueValue())
            .build();
    }
}
