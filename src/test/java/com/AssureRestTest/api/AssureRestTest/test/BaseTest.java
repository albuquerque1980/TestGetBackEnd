package com.AssureRestTest.api.AssureRestTest.test;

import io.restassured.RestAssured;
import org.springframework.test.context.event.annotation.BeforeTestClass;

public class BaseTest {
    String basePath = "https://reqres.in/api";
    String USER_REGISTER = "/register";
    Integer responseId = 4;
    String responseToken = "QpwL5tke4Pnpja7X4";
    String errorMissingPassword = "Missing password";
    String errorMissingUser = "Missing email or username";
    String errorInvalidUser = "Note: Only defined users succeed registration";

    @BeforeTestClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
