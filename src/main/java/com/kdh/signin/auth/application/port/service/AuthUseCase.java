package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.domain.User;

/**
 * @author han
 */
public interface AuthUseCase {

    /**
     * @param command
     * @return jwt token
     */
    String signUp(SignUpCommand command);

    /**
     * @param identifier
     * @return
     */
    User findMyInfo(String identifier);

    void resetPassword(String key);
}
