package com.kdh.signin;

import com.kdh.signin.auth.adapter.in.web.ResetPasswordRequest;
import com.kdh.signin.auth.adapter.in.web.SignUpRequest;
import com.kdh.signin.auth.adapter.in.web.SigninRequest;
import com.kdh.signin.auth.adapter.out.web.SigninResponse;
import com.kdh.signin.auth.adapter.out.web.UserInfoResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.*;

/**
 * @author han
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    String email = "test1@gmail.com";
    String nickName = "hello";
    String password = "password";
    String name = "song";
    String phoneNumber = "010-1234-5678";
    String token = "verified";

    @Test
    @Order(1)
    void signUp() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/auth/signup";
        URI uri = new URI(baseUrl);

        SignUpRequest signUpRequest = new SignUpRequest(email, nickName, password, name, phoneNumber, token);

        ResponseEntity<String> re1 = testRestTemplate.postForEntity(uri, signUpRequest, String.class);
        assertEquals(OK, re1.getStatusCode());

        ResponseEntity<String> re2 = testRestTemplate.postForEntity(uri, signUpRequest, String.class);
        assertEquals(BAD_REQUEST, re2.getStatusCode());
    }

    @Test
    @Order(2)
    void signIn() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/auth/signin";
        URI uri = new URI(baseUrl);

        SigninRequest requestWithEmail = new SigninRequest(email, null, password);

        ResponseEntity<SigninResponse> re1 = testRestTemplate.postForEntity(uri, requestWithEmail, SigninResponse.class);
        assertEquals(OK, re1.getStatusCode());

        SigninRequest requestWithPhone = new SigninRequest(null, phoneNumber, password);

        ResponseEntity<SigninResponse> re2 = testRestTemplate.postForEntity(uri, requestWithPhone, SigninResponse.class);
        assertEquals(OK, re2.getStatusCode());
    }

    @Test
    @Order(3)
    void signInFail() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/auth/signin";
        URI uri = new URI(baseUrl);

        SigninRequest requestWithEmptyString = new SigninRequest("", phoneNumber, password);
        ResponseEntity<String> re1 = testRestTemplate.postForEntity(uri, requestWithEmptyString, String.class);
        assertEquals(BAD_REQUEST, re1.getStatusCode());
        assertEquals("Email can not be null or empty", re1.getBody());

        SigninRequest requestWithWrongPassword = new SigninRequest(null, phoneNumber, "wrong-password");
        ResponseEntity<String> re2 = testRestTemplate.postForEntity(uri, requestWithWrongPassword, String.class);
        assertEquals(BAD_REQUEST, re2.getStatusCode());
        assertEquals("Password not matched", re2.getBody());
    }

    @Test
    @Order(4)
    void findMyInfo() throws URISyntaxException {
        final String signInUri = "http://localhost:" + port + "/auth/signin"; // because only 1 user already register
        URI uri = new URI(signInUri);

        SigninRequest sr = new SigninRequest(null, phoneNumber, password);
        ResponseEntity<SigninResponse> re1 = testRestTemplate.postForEntity(uri, sr, SigninResponse.class);
        String token = re1.getBody().getToken();

        final String infoUri = "http://localhost:" + port + "/auth/info/1";

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", token);

        ResponseEntity<UserInfoResponse> re2 = testRestTemplate.exchange(infoUri, HttpMethod.GET, new HttpEntity<>(headers), UserInfoResponse.class);
        assertEquals(OK, re2.getStatusCode());

        UserInfoResponse body = re2.getBody();
        assertEquals(name ,body.getName());
        assertEquals(nickName ,body.getNickName());
        assertEquals(phoneNumber ,body.getPhoneNumber());
        assertEquals(email ,body.getEmail());
    }

    @Test
    @Order(5)
    void findMyInfoFail() {
        final String infoUri = "http://localhost:" + port + "/auth/info/1";

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", "");

        ResponseEntity<String> re2 = testRestTemplate.exchange(infoUri, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        assertEquals(UNAUTHORIZED, re2.getStatusCode());
    }

    @Test
    @Order(6)
    void resetPassword() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/auth/signin";
        URI uri = new URI(baseUrl);
        SigninRequest sr1 = new SigninRequest(email, null, password);
        ResponseEntity<SigninResponse> re1 = testRestTemplate.postForEntity(uri, sr1, SigninResponse.class);
        assertEquals(OK, re1.getStatusCode());

        final String resetUrl = "http://localhost:" + port + "/auth/reset";
        URI uri2= new URI(resetUrl);
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(phoneNumber, "mock token", "newPassword");
        ResponseEntity<String> re2 = testRestTemplate.postForEntity(uri2, resetPasswordRequest, String.class);
        assertEquals(OK, re2.getStatusCode());

        SigninRequest sr2 = new SigninRequest(email, null, password);
        ResponseEntity<String> re3 = testRestTemplate.postForEntity(uri, sr2, String.class);
        assertEquals(BAD_REQUEST, re3.getStatusCode());

        SigninRequest sr3 = new SigninRequest(email, null, "newPassword");
        ResponseEntity<SigninResponse> re4 = testRestTemplate.postForEntity(uri, sr3, SigninResponse.class);
        assertEquals(OK, re4.getStatusCode());
    }
}
