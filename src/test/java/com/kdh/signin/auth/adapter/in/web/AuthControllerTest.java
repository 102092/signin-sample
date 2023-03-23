package com.kdh.signin.auth.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdh.signin.auth.adapter.in.web.AuthController;
import com.kdh.signin.auth.adapter.in.web.SignUpRequest;
import com.kdh.signin.auth.application.port.service.AuthService;
import com.kdh.signin.auth.application.port.service.VerifyPhoneService;
import com.kdh.signin.auth.domain.Phone;
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

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VerifyPhoneService phoneService;

    @MockBean
    AuthService authService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void checkPhoneNumber() throws Exception {
        //given
        given(phoneService.verity(any(Phone.class))).willReturn(Boolean.TRUE);

        //then
        mockMvc.perform(post("/auth/phone")
                .header("Content-Type", "application/json")
                .content("010-1234-4567"))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.verified").value(Boolean.TRUE),
                jsonPath("$.phone_number").value("010-1234-4567")
            );
    }

    @Test
    void throwExceptionIfPhoneNumberIsNotValid() throws Exception {
        mockMvc.perform(post("/auth/phone")
                .header("Content-Type", "application/json")
                .content("010-1234-45671"))
            .andExpectAll(
                status().is4xxClientError());
    }

    @Test
    void signUpSuccess() throws Exception {

        SignUpRequest signUpRequest = new SignUpRequest("test@gmail.com", "hello", "name", "password", "phone");

        mockMvc.perform(post("/auth/signup")
                .header("Content-Type", "application/json")
                .content(objectMapper.writeValueAsString(signUpRequest)))
            .andExpect(
                status().isOk()
            );
    }
}