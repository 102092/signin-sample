package com.kdh.signin.auth.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * @author han
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final UserId id;

    /**
     * 유저 이메일, 식별자 사용 가능 (unique)
     */
    private final Email email;

    /**
     * 유저 닉네임
     */
    private final NickName nickName;

    /**
     * 유저 패스워드 (encrypted)
     */
    private final Password password;

    /**
     * 유저 이름
     */
    private final Name name;

    /**
     * 유저 전화번호 (encrypted)
     */
    private final Phone phone;


    @Value
    private static class UserId {
        Long id;
    }
}
