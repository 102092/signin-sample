package com.kdh.signin.auth.domain;

import lombok.Value;

/**
 * @author han
 */

@Value
public class NickName {

    String value;

    public NickName(String value) {
        this.value = value;
    }
}
