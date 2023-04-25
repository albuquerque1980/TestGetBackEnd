package com.AssureRestTest.api.AssureRestTest.test;

import com.AssureRestTest.api.AssureRestTest.dom.User;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class UserTest extends BaseTest {

    @Test
    public void testRegisterOk_200(){
        User user = new User("eve.holt@reqres.in","pistol");
        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post(basePath + USER_REGISTER).
                then().
                statusCode(HttpStatus.SC_OK).
                body("id",equalTo(responseId)).
                body("token",equalTo(responseToken))
                .extract()
                .as(User.class);
    }

    @Test
    public void testRegisterWithoutUserName_400(){
        User user = new User("eve.holt@reqres.in","pistol");
        String password = "{" + '"' + "password" + '"' + ":" + '"' + user.getPassword() + '"' + "}";
        given().
                contentType(ContentType.JSON).
                body(password).
                when().
                post(basePath + USER_REGISTER).
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error",equalTo(errorMissingUser))
                .extract()
                .as(User.class);
    }

    @Test
    public void testRegisterWithoutPassword_400(){
        User user = new User("eve.holt@reqres.in","pistol");
        String email = "{" + '"' + "email" + '"' + ":" + '"' + user.getEmail() + '"' + "}";
        given().
                contentType(ContentType.JSON).
                body(email).
                when().
                post(basePath + USER_REGISTER).
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error",equalTo(errorMissingPassword))
                .extract()
                .as(User.class);
    }

    @Test
    public void testRegisterInvalidUser_400(){
        User user = new User("111111","pistol");
        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post(basePath + USER_REGISTER).
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error",equalTo(errorInvalidUser))
                .extract()
                .as(User.class);
    }

    @Test
    public void testPostRegisterBodyEmpty_400(){
        User user = new User();
        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post(basePath + USER_REGISTER).
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error",equalTo(errorMissingUser));
    }

    @Test
    public void testRegisterUserNameEmpty_400(){
        User user = new User("","pistol");
        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post(basePath + USER_REGISTER).
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error",equalTo(errorMissingUser))
                .extract()
                .as(User.class);
    }

    @Test
    public void testRegisterPasswordEmpty_400() {
        User user = new User("eve.holt@reqres.in", "");
        given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post(basePath + USER_REGISTER).
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error", equalTo(errorMissingPassword))
                .extract()
                .as(User.class);
    }
}
