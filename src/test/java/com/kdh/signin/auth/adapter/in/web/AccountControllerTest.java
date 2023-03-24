package com.kdh.signin.auth.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdh.signin.auth.application.port.in.SignInCommand;
import com.kdh.signin.auth.application.port.service.AccountService;
import com.kdh.signin.auth.application.port.service.VerifyPhoneMockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
    AccountService authService;

    @Autowired
    ObjectMapper objectMapper;

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

        mockMvc.perform(post("/auth/signup")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(signUpRequest)))
            .andExpect(
                status().isOk());
    }

    @Test
    void signInSuccess() throws Exception {

        given(authService.signIn(any(SignInCommand.class))).willReturn("jwttoken");

        SigninRequest request = new SigninRequest("test@gmail.com", "010-1234-5678", "password");

        mockMvc.perform(post("/auth/signin")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(
                status().isOk());
    }
}