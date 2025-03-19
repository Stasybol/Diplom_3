package site.nomoreparties.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.model.Credentials;
import site.nomoreparties.stellarburgers.model.User;
import static io.restassured.RestAssured.given;


public class BurgerServiceClient {
    private final String baseURI;
    private static final String POST_CREATE_USER = "/api/auth/register";
    private static final String POST_LOGIN_USER = "/api/auth/login";
    private static final String DELETE_USER = "/api/auth/user";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String TYPE_JSON = "application/json";
    private static final String HEADER_AUTHORIZATION = "authorization";

    public BurgerServiceClient(String baseURI) {
        this.baseURI = baseURI;
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user){
        return given()
                .log()
                .all()
                .baseUri(baseURI)
                .header(HEADER_CONTENT_TYPE, TYPE_JSON)
                .body(user)
                .post(POST_CREATE_USER)
                .then()
                .log()
                .all();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(Credentials credentials){
        return given()
                .log()
                .all()
                .baseUri(baseURI)
                .header(HEADER_CONTENT_TYPE, TYPE_JSON)
                .body(credentials)
                .post(POST_LOGIN_USER)
                .then()
                .log()
                .all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token){
        return given()
                .log()
                .all()
                .baseUri(baseURI)
                .header(HEADER_CONTENT_TYPE, TYPE_JSON)
                .header(HEADER_AUTHORIZATION, token)
                .delete(DELETE_USER)
                .then()
                .log()
                .all();
    }
}

