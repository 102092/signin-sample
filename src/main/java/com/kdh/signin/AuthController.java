package com.kdh.signin;

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

    @PostMapping(value = "auth/phone")
    public ResponseEntity<PhoneVerifyResponse> checkPhoneNumber(@RequestBody String body) {
        Phone phone = new Phone(body);
        return ResponseEntity.ok(new PhoneVerifyResponse(phoneService.verity(phone), phone.getValue()));
    }
}
