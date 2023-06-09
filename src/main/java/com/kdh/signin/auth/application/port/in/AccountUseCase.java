package com.kdh.signin.auth.application.port.in;

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
     * @param command
     * @return jwtString (with id)
     */
    String signIn(SignInCommand command);

    /**
     * @param id
     * @return User
     */
    User findMyInfo(User.UserId id);

    /**
     * @param command
     */
    void resetPassword(ResetPasswordCommand command);

}
