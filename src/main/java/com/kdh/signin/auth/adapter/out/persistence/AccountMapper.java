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
            .password(password.getValue())
            .name(name.getValue())
            .phoneNumber(phone.getUniqueValue())
            .build();
    }

    User mapToDomain(AccountJpaEntity entity) {
        return User.builder()
            .id(new User.UserId(entity.getId()))
            .email(new Email(entity.getEmail()))
            .password(new Password(entity.getPassword()))
            .name(new Name(entity.getName()))
            .nickName(new NickName(entity.getNickName()))
            .phone(new Phone(entity.getPhoneNumber()))
            .build();
    }
}
