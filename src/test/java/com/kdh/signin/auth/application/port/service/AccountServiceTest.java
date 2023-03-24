package com.kdh.signin.auth.application.port.service;

import com.kdh.signin.auth.adapter.out.persistence.AccountPersistenceAdapter;
import com.kdh.signin.auth.application.port.in.SignInCommand;
import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.domain.*;
import com.kdh.signin.common.JwtHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author han
 */

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountPersistenceAdapter adapter;

    @InjectMocks
    AccountService service;

    @Test
    void signUpDuplicated() {
        //given
        given(adapter.isSignUp(any(Email.class), any(Phone.class))).willReturn(Boolean.TRUE);
        SignUpCommand command = mock(SignUpCommand.class);

        //when
        assertThrows(RuntimeException.class, () -> service.signUp(command));
    }

    @Test
    void throwExceptionWhenSignInButNotSignUp() {
        //given
        SignInCommand command = mock(SignInCommand.class);
        given(adapter.isNotSignUp(any(Email.class), any(Phone.class))).willReturn(Boolean.TRUE);
        given(command.getPhone()).willReturn(Phone.NULL_OBJECT);
        given(command.getEmail()).willReturn(Email.NULL_OBJECT);

        //when
        assertThrows(NoSuchElementException.class, () -> service.signIn(command));
    }

    @Test
    void whenPhoneIsNullObject() {
        // given
        SignInCommand command = mock(SignInCommand.class);
        given(adapter.isNotSignUp(any(Email.class), any(Phone.class))).willReturn(Boolean.FALSE);
        given(adapter.findByEmail(any(Email.class))).willReturn(User.builder().id(new User.UserId(1L)).password(new Password("1")).build());
        given(command.getPhone()).willReturn(Phone.NULL_OBJECT);
        given(command.getEmail()).willReturn(new Email("test@gmail.com"));
        given(command.getPassword()).willReturn(new Password("1"));

        // when
        service.signIn(command);

        //then
        verify(adapter, atLeastOnce()).findByEmail(any(Email.class));
    }

    @Test
    void whenEmailIsNullObject() {
        // given
        SignInCommand command = mock(SignInCommand.class);
        given(adapter.isNotSignUp(any(Email.class), any(Phone.class))).willReturn(Boolean.FALSE);
        given(adapter.findByPhone(any(Phone.class))).willReturn(User.builder().id(new User.UserId(1L)).password(new Password("1")).build());
        given(command.getPhone()).willReturn(new Phone("010-1234-4567"));
        given(command.getEmail()).willReturn(Email.NULL_OBJECT);
        given(command.getPassword()).willReturn(new Password("1"));

        // when
        service.signIn(command);

        //then
        verify(adapter, atLeastOnce()).findByPhone(any(Phone.class));
    }
}