package cases;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

/*
1. Проверка, что создание пользователя с корректным запросом работает
    Отправить запрос на https://petstore.swagger.io/v2/user, в теле запроса указать параметр username
    ОР: в ответе статус код 200, в теле ответа параметр code = 200
2. Проверка, что при создании пользователя с не корректным запросом ошибка
    Отправить запрос на https://petstore.swagger.io/v2/user, в теле запроса указать параметр username,
    который не зарегистрирован
    ОР: в ответе статус код 200, в теле ответа параметр code = 200, параметр message = "0"
3. Проверка, что при запросе данных существующего пользователя ошибок нет
    Отправить запрос на https://petstore.swagger.io/v2/user/{username}, где в качестве параметра username указать имя
    существующего в системе пользователя
    ОР: в ответе статус код 200, в теле ответа параметр username = username указанного пользователя
4. Проверка, что при запросе данных не существующего пользователя ошибока
    Отправить запрос на https://petstore.swagger.io/v2/user/{username}, где в качестве параметра username указать имя
    не существующего в системе пользователя
    ОР: в ответе статус код 404, в теле ответа параметр message = User not found
*/

public class HomeWork06Test {

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io/v2")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();

    private static final String createUser = "/user";
    private static final String getUser = "/user/{username}";

    private String userJson = "{\"username\":\"username\"}";

    @Test
    public void createUser() {
        given()
                .spec(requestSpecification)
                .body(userJson)
                .post(HomeWork06Test.createUser)
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    @Test
    public void createWrongUser() {
        given()
                .spec(requestSpecification)
                .body("{}")
                .post(HomeWork06Test.createUser)
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("0"));
    }

    @Test
    public void getUserByName() {
        String username = "username";
        given()
                .spec(requestSpecification)
                .get(HomeWork06Test.getUser, username)
        .then()
        .statusCode(200)
        .body("username", equalTo(username));
    }

    @Test
    public void getUserByWrongName() {
        String username = UUID.randomUUID().toString();
        given()
                .spec(requestSpecification)
                .get(HomeWork06Test.getUser, username)
                .then()
                .statusCode(404)
                .body("message", equalTo("User not found"));
    }
}
