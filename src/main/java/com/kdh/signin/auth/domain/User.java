package com.kdh.signin.auth.domain;

import lombok.*;

/**
 * @author han
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@Builder
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
     * 유저 전화번호, 식별자로 사용 가능 (encrypted, unique)
     */
    private final Phone phone;

    @Value
    public static class UserId {
        Long id;
    }
}
