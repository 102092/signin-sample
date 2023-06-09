package com.kdh.signin.auth.adapter.in.web;

import com.kdh.signin.auth.adapter.out.web.SignUpResponse;
import com.kdh.signin.auth.adapter.out.web.SigninResponse;
import com.kdh.signin.auth.adapter.out.web.UserInfoResponse;
import com.kdh.signin.auth.application.port.in.AccountUseCase;
import com.kdh.signin.auth.application.port.in.ResetPasswordCommand;
import com.kdh.signin.auth.application.port.in.SignInCommand;
import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.adapter.out.web.PhoneVerifyResponse;
import com.kdh.signin.auth.application.port.service.VerifyPhoneMockService;
import com.kdh.signin.auth.domain.*;
import com.kdh.signin.common.error.BadRequestException;
import com.kdh.signin.common.JwtHelper;
import com.kdh.signin.common.error.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author han
 */

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final VerifyPhoneMockService phoneService;

    private final AccountUseCase accountService;

    @PostMapping(value = "auth/phone")
    public ResponseEntity<PhoneVerifyResponse> checkPhoneNumber(@RequestBody String phoneNumber) {
        return ResponseEntity.ok(new PhoneVerifyResponse(phoneService.verify(phoneNumber), phoneNumber));
    }

    @PostMapping(value = "auth/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        Email email = Email.of(request.getEmail());
        NickName nickName = new NickName(request.getNickName());
        Password password = new Password(request.getPassword());
        Name name = new Name(request.getName());
        Phone phone = new Phone(request.getPhoneNumber());

        SignUpCommand command = SignUpCommand.builder()
            .email(email)
            .nickName(nickName)
            .password(password)
            .name(name)
            .phone(phone)
            .token(request.getToken())
            .build();

        if (!phoneService.verifyToken(command.getToken())) {
            throw new IllegalArgumentException("This token is not valid");
        }

        return ResponseEntity.ok(new SignUpResponse(accountService.signUp(command)));
    }

    @PostMapping(value = "auth/signin")
    public ResponseEntity<SigninResponse> sigin(@RequestBody SigninRequest request) {
        Email email = request.getEmail() == null ? Email.NULL_OBJECT : Email.of(request.getEmail());
        Password password = new Password(request.getPassword());
        Phone phone = request.getPhoneNumber() == null ? Phone.NULL_OBJECT : new Phone(request.getPhoneNumber());

        SignInCommand command = SignInCommand.builder()
            .email(email)
            .password(password)
            .phone(phone)
            .build();

        return ResponseEntity.ok(new SigninResponse(accountService.signIn(command)));
    }

    @GetMapping(value = "auth/info/{id}")
    public ResponseEntity<UserInfoResponse> info(@RequestHeader(value = "x-auth-token") String token, @PathVariable(value = "id") Long id) {

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Only logged-in users can access it");
        }

        User decode = JwtHelper.decode(token);

        boolean isNotSameUser = !decode.getId().getId().equals(id);
        if (isNotSameUser) {
            throw new BadRequestException("You can only view your own information");
        }

        User info = accountService.findMyInfo(decode.getId());

        UserInfoResponse response = UserInfoResponse.builder()
            .email(info.getEmail())
            .nickName(info.getNickName())
            .phoneNumber(info.getPhone())
            .name(info.getName())
            .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "auth/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {

        if (!phoneService.verifyToken(request.getToken())) {
            throw new IllegalArgumentException("This token is not valid");
        }

        Phone phone = new Phone(request.getPhoneNumber());
        Password password = new Password(request.getPassword());
        ResetPasswordCommand command = new ResetPasswordCommand(phone, password);
        accountService.resetPassword(command);

        return ResponseEntity.ok("Reset completed. Please re-login");
    }
}
