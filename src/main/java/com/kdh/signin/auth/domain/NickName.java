package com.kdh.signin.auth.domain;

import lombok.Getter;

/**
 * @author han
 */

@Getter
public class NickName {

    private final String value;

    public NickName(String value) {
        this.value = value;
    }
}
