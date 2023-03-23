package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.adapter.out.persistence.AccountPersistenceAdapter;
import com.kdh.signin.auth.application.port.in.AccountUseCase;
import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.domain.Email;
import com.kdh.signin.auth.domain.Identifier;
import com.kdh.signin.auth.domain.Phone;
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

    private final AccountPersistenceAdapter adapter;

    @Override
    public Long signUp(SignUpCommand command) {
        Phone phone = command.getPhone();
        Email email = command.getEmail();

        if (adapter.isSignUp(email, phone)) {
            throw new RuntimeException("Duplicated request");
        }

        return adapter.save(email, command.getNickName(), command.getPassword(), command.getName(), command.getPhone());
    }

    @Override
    public User findMyInfo(Identifier identifier) {
        return null;
    }

    @Override
    public void resetPassword(String key) {

    }
}
