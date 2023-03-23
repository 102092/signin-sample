package com.kdh.signin.auth.domain;

/**
 * @author han
 */
public interface Identifier {

    /**
     * @return string of unique value from user
     */
    String getUniqueValue();
}
