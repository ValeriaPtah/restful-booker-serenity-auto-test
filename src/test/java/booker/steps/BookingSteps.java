package booker.steps;

import booker.BaseBookerTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.hasKey;

public class BookingSteps {

    private Response response;
    private String authToken;
    private final String basePath = "/booking";
    private final SharedSteps sharedSteps = new SharedSteps();
    private final File bookingSchema = BaseBookerTest.getJsonSchema("booking-schema.json");

    @Step("When requesting list of all bookings")
    public void whenRequestingListOfBookings() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        response = SerenityRest.given(requestSpec).get(basePath);
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
        authToken = response.path("token");
    }

    @Step("List of bookings is returned")
    public void listOfBookingsIsReturned() {
        response.then().body(matchesJsonSchema(bookingSchema));
    }

}
