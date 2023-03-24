package com.kdh.signin.auth.adapter.in.web;

import com.kdh.signin.auth.application.port.in.AccountUseCase;
import com.kdh.signin.auth.application.port.in.SignInCommand;
import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.application.port.out.PhoneVerifyResponse;
import com.kdh.signin.auth.application.port.service.VerifyPhoneMockService;
import com.kdh.signin.auth.domain.*;
import com.kdh.signin.common.BadRequestException;
import com.kdh.signin.common.JwtHelper;
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
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        Email email = new Email(request.getEmail());
        NickName nickName = new NickName(request.getNickName());
        Password password = new Password(request.getPassword());
        Name name = new Name(request.getName());
        Phone phone = Phone.of(request.getPhoneNumber());

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

        return ResponseEntity.ok(String.valueOf(accountService.signUp(command)));
    }

    @PostMapping(value = "auth/signin")
    public ResponseEntity<String> sigin(@RequestBody SigninRequest request) {
        Email email = request.getEmail() == null ? Email.NULL_OBJECT : new Email(request.getEmail());
        Password password = new Password(request.getPassword());
        Phone phone = request.getPhoneNumber() == null ? Phone.NULL_OBJECT : new Phone(request.getPhoneNumber());

        SignInCommand command = SignInCommand.builder()
            .email(email)
            .password(password)
            .phone(phone)
            .build();

        return ResponseEntity.ok(accountService.signIn(command));
    }

    @GetMapping(value = "auth/info/{id}")
    public ResponseEntity<UserInfoResponse> info(@RequestHeader(value = "x-auth-token") String token,
        @PathVariable(value = "id") Long id) {

        if (token == null || token.isEmpty()) {
            throw new BadRequestException("로그인한 유저만 접근할 수 있습니다");
        }

        User decode = JwtHelper.decode(token);

        if (!decode.getId().getId().equals(id)) {
            throw new BadRequestException("자기 자신의 정보만 조회할 수 있습니다.");
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
}
