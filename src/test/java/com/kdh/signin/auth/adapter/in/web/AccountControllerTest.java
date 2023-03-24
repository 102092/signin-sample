package com.kdh.signin.auth.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdh.signin.auth.application.port.in.AccountUseCase;
import com.kdh.signin.auth.application.port.in.SignInCommand;
import com.kdh.signin.auth.application.port.service.VerifyPhoneMockService;
import com.kdh.signin.auth.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author han
 */

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VerifyPhoneMockService phoneService;

    @MockBean
    AccountUseCase accountUseCase;

    @Autowired
    ObjectMapper objectMapper;

    String code = "eyJhbGciOiJIUzUxMiJ9.eyJpIjoxfQ.1CHbz5rG9hIK5kOUCkfnqbIXFWEY4kIQxtuBNe4L1Ka2VQdiEtb0qjBZRrgUodRfTxP0ZoPRj5gZy2Wl81izVA"; // code
    @Test
    void checkPhoneNumber() throws Exception {
        //given
        given(phoneService.verify(any(String.class))).willReturn("token");

        //then
        mockMvc.perform(post("/auth/phone")
                .header("Content-Type", "application/json")
                .content("010-1234-4567"))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.token").value("token"),
                jsonPath("$.phone_number").value("010-1234-4567")
            );
    }

    @Test
    void signUpSuccess() throws Exception {

        SignUpRequest signUpRequest = new SignUpRequest("test@gmail.com", "hello", "name", "password", "010-1234-5678", "token");
        given(phoneService.verifyToken(any(String.class))).willReturn(Boolean.TRUE);

        mockMvc.perform(post("/auth/signup")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(signUpRequest)))
            .andExpect(
                status().isOk());
    }

    @Test
    void signInSuccess() throws Exception {

        given(accountUseCase.signIn(any(SignInCommand.class))).willReturn("jwttoken");

        SigninRequest request = new SigninRequest("test@gmail.com", "010-1234-5678", "password");

        mockMvc.perform(post("/auth/signin")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(
                status().isOk());
    }

    @Test
    void throwExceptionIfTokenEmpty() throws Exception {
        mockMvc.perform(get("/auth/info/{id}", 1L)
                .header("Content-Type", "application/json")
                .header("x-auth-token", ""))
            .andExpect(
                status().is4xxClientError());
    }

    @Test
    void throwExceptionIfDecodeIsNull() throws Exception {
        mockMvc.perform(get("/auth/info/{id}", 2L)
                .header("Content-Type", "application/json")
                .header("x-auth-token", code))
            .andExpect(
                status().is4xxClientError());
    }

    @Test
    void findInfoSuccess() throws Exception {
        User u = User.builder()
            .email(new Email("test@gmail.com"))
            .nickName(new NickName("nickname"))
            .phone(new Phone("010-1234-4567"))
            .name(new Name("name")).build();
        given(accountUseCase.findMyInfo(new User.UserId(1L))).willReturn(u);

        mockMvc.perform(get("/auth/info/{id}", 1L)
                .header("Content-Type", "application/json")
                .header("x-auth-token", code))
            .andExpect(
                status().isOk());
    }
}