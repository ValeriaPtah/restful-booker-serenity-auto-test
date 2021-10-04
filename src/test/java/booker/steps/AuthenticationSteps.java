package booker.steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.hasKey;

public class AuthenticationSteps {

    private final SharedSteps sharedSteps = new SharedSteps();
    private final String basePath = "/auth";
    private Response response;

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
        sharedSteps.responseIsSuccessful(response);
    }

    @Step("Response is unauthorized")
    public void responseIsUnauthorized() {
        sharedSteps.responseIsUnauthorized(response);
    }

    @Step("Authentification token is generated")
    public void authTokenIsGenerated() {
        response.then().body("$", hasKey("token"));
    }

}
