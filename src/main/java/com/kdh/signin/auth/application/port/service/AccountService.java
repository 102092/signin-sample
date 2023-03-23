package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.domain.Identifier;
import com.kdh.signin.auth.domain.User;
import com.kdh.signin.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author han
 */

@UseCase
@Transactional
@RequiredArgsConstructor
public class AccountService implements AccountUseCase {
    @Override
    public String signUp(SignUpCommand command) {
        throwIfAlreadySignUp();
        return null;
    }

    @Override
    public User findMyInfo(Identifier identifier) {
        return null;
    }

    @Override
    public void resetPassword(String key) {

    }

    private void throwIfAlreadySignUp() {

    }
}
