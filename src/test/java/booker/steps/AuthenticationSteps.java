package booker.steps;

import booker.BaseBookerTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.hasKey;

public class AuthenticationSteps extends BaseBookerTest {

    private Response response;
    private String authToken;
    private final String basePath = "/auth";

    @Step("Given password {0} and login {1}")
    public void givenCredentials(String pass, String login) {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBody("{ \"username\" : \"" + login + "\", \"password\" : \"" + pass + "\"}")
                .build();

        response = SerenityRest.given(requestSpec).post(basePath);
    }

    @Step("Response is successful")
    public void responseIsSuccessful() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Step("Authentification token is generated")
    public void authTokenIsGenerated() {
        response.then().body("$", hasKey("token"));
        authToken = response.path("token");
    }

}
