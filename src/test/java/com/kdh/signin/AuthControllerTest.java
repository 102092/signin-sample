package com.kdh.signin;

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

    @Test
    void checkPhoneNumber() throws Exception {
        //given
        given(phoneService.verity(any(Phone.class))).willReturn("token");

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
    void throwExceptionIfPhoneNumberIsNotValid() throws Exception {
        mockMvc.perform(post("/auth/phone")
                .header("Content-Type", "application/json")
                .content("010-1234-45671"))
            .andExpectAll(
                status().is4xxClientError());
    }
}