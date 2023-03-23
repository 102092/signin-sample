package com.kdh.signin.auth.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author han
 */

@Entity
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
class AccountJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column
    private ZonedDateTime createdAt;

    @Builder
    protected AccountJpaEntity(Long id, String email, String nickName, String password, String name, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = ZonedDateTime.now();
    }
}
