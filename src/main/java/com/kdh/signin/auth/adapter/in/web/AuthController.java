package com.kdh.signin.auth.adapter.in.web;

import com.kdh.signin.*;
import com.kdh.signin.auth.application.port.in.SignUpCommand;
import com.kdh.signin.auth.application.port.out.PhoneVerifyResponse;
import com.kdh.signin.auth.application.port.service.AuthService;
import com.kdh.signin.auth.application.port.service.VerifyPhoneService;
import com.kdh.signin.auth.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author han
 */

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final VerifyPhoneService phoneService;

    private final AuthService authService;

    @PostMapping(value = "auth/phone")
    public ResponseEntity<PhoneVerifyResponse> checkPhoneNumber(@RequestBody String body) {
        Phone phone = new Phone(body);
        return ResponseEntity.ok(new PhoneVerifyResponse(phoneService.verity(phone), phone.getValue()));
    }

    @PostMapping(value = "auth/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        Email email = new Email(request.getEmail());
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
            .build();

        return ResponseEntity.ok("hi");
    }
}