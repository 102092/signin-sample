package com.kdh.signin.auth.application.port.in;

import com.kdh.signin.auth.domain.Identifier;
import com.kdh.signin.auth.domain.User;

/**
 * @author han
 */
public interface AccountUseCase {

    /**
     * @param command
     * @return accountId
     */
    Long signUp(SignUpCommand command);

    /**
     * @param identifier
     * @return
     */
    User findMyInfo(Identifier identifier);

    void resetPassword(String key);
}
