package com.kdh.signin.common;

import com.kdh.signin.auth.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author han
 */
class JwtHelperTest {

    Email email = new Email("email@gmail.com");
    Password password = new Password("password");
    Name name = new Name("name");
    NickName nickName = new NickName("nickname");
    Phone phone = new Phone("010-1234-4567");


    @Test
    void encodeAndDecode() {
        User u = User.builder()
            .id(new User.UserId(1L))
            .email(email)
            .password(password)
            .name(name)
            .nickName(nickName)
            .phone(phone)
            .build();

        String encode = JwtHelper.encode(u);
        User decode = JwtHelper.decode(encode);

        assertEquals(decode, u);
    }

}