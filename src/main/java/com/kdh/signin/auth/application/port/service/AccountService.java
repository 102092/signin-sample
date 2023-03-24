package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.adapter.out.persistence.AccountPersistenceAdapter;
import com.kdh.signin.auth.application.port.in.AccountUseCase;
import com.kdh.signin.auth.application.port.in.SignInCommand;
import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.domain.*;
import com.kdh.signin.auth.domain.User.UserId;
import com.kdh.signin.common.JwtHelper;
import com.kdh.signin.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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
    @Transactional(readOnly = true)
    public String signIn(SignInCommand command) {
        Phone phone = command.getPhone();
        Email email = command.getEmail();

        if (adapter.isNotSignUp(email, phone)) {
            throw new NoSuchElementException("There is no user matched by email {" + email.getUniqueValue() + "}" + " or phone {" + phone.getUniqueValue() + "}");
        }

        Password password = command.getPassword();

        if (phone.equals(Phone.NULL_OBJECT)) {
            User user = adapter.findByEmail(email);
            throwIfPasswordNotMatched(user.getPassword(), password);
            return JwtHelper.encode(user);
        }

        User user = adapter.findByPhone(phone);
        throwIfPasswordNotMatched(user.getPassword(), password);

        return JwtHelper.encode(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findMyInfo(UserId id) {
        return adapter.findById(id.getId());
    }

    @Override
    public void resetPassword(String key) {

    }

    private void throwIfPasswordNotMatched(Password pwFromDb, Password pwFromRequest) {
        pwFromDb.verified(pwFromRequest);
    }
}
