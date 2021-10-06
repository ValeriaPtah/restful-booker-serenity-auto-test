package booker.util;

import booker.model.Booking;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasToString;

public class BookingSteps {

    private final File bookingListSchema = BookingHelper.getJsonSchema("booking-list-schema.json");
    private final File createdBookingSchema = BookingHelper.getJsonSchema("created-booking-schema.json");
    private final File existingBookingSchema = BookingHelper.getJsonSchema("existing-booking-schema.json");
    private final Booking testBooking = BookingHelper.testBookingEntry();
    private Response response;
    private Integer createdBookingID;

    @Step("Response is successful")
    public void responseIsSuccessful() {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Step("Response is created")
    public void responseIsCreated() {
        response.then().statusCode(HttpStatus.SC_CREATED);
    }

    @Step("Response is unauthorized")
    public void responseIsUnauthorized() {
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("Response is not found")
    public void responseIsNotFound() {
        response.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Step("Response is forbidden")
    public void responseIsForbidden() {
        response.then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Step("Given password {0} and login {1}")
    public void givenCredentials(String pass, String login) {
        String path = "/auth";
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setBody("{ \"username\" : \"" + login + "\", \"password\" : \"" + pass + "\"}")
                .setContentType(ContentType.JSON).build();
        response = SerenityRest.given(requestSpec).post(path);
    }

    @Step("When requesting list of all bookings")
    public void whenRequestingListOfBookings() {
        String path = "/booking";
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .build();
        response = SerenityRest.given(requestSpec).get(path);
    }

    @Step("When given an existing booking ID")
    public void givenCorrectBookingIdIsProvided(Integer bookingID) {
        String path = "/booking/" + bookingID;
        RequestSpecification requestSpec = new RequestSpecBuilder().build();
        response = SerenityRest.given(requestSpec).get(path);
    }

    @Step("Given correct booking information")
    public void givenCorrectBookingInput() {
        String path = "/booking";
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody(BookingHelper.toJson(testBooking, Booking.class))
                .build();
        response = SerenityRest.given(requestSpec).post(path);
    }

    @Step("Update to an existing booking is applied")
    public void updateIsApplied(Integer bookingID) {
        String path = "/booking/" + bookingID;
        String authToken = BookingHelper.token();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addCookie("token", authToken)
                .setContentType(ContentType.JSON)
                .setBody(BookingHelper.toJson(testBooking, Booking.class))
                .build();
        response = SerenityRest.given(requestSpec).put(path);
    }

    @Step("Try to update an existing booking without authorisation")
    public void updateWithoutToken(Integer bookingID) {
        String path = "/booking/" + bookingID;
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody(BookingHelper.toJson(testBooking, Booking.class))
                .build();
        response = SerenityRest.given(requestSpec).put(path);
    }

    @Step("Patch to an existing booking is applied and verified")
    public void patchWithToken(Integer bookingID) {
        String path = "/booking/" + bookingID;
        String authToken = BookingHelper.token();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addCookie("token", authToken)
                .setContentType(ContentType.JSON)
                .setBody("{ \"firstname\" : \"Jane\", \"lastname\" : \"Doe\"}")
                .build();
        response = SerenityRest.given(requestSpec).patch(path);
    }

    @Step("Try to patch an existing booking without authorisation")
    public void patchWithoutToken(Integer bookingID) {
        String path = "/booking/" + bookingID;
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody("{ \"firstname\" : \"Jane\", \"lastname\" : \"Doe\"}")
                .build();
        response = SerenityRest.given(requestSpec).patch(path);
    }

    @Step("Authentification token is generated")
    public void authTokenIsGenerated() {
        response.then().body("$", hasKey("token"));
    }

    @Step("List of bookings is returned")
    public void listOfBookingsIsReturned() {
        response.then().body(matchesJsonSchema(bookingListSchema));
    }

    @Step("Full booking info is returned")
    public void createdBookingInfoIsReturned() {
        createdBookingID = response.then().extract().response().path("bookingid");
        response.then().body(matchesJsonSchema(createdBookingSchema));
    }

    @Step("Full booking info is returned")
    public void fullBookingInfoIsReturned() {
        response.then().body(matchesJsonSchema(existingBookingSchema));
    }

    @Step("Update to an existing booking is verified")
    public void bookingEntryIsUpdated() {
        response.then().body("firstname", hasToString(testBooking.getFirstName()));
    }

    @Step("Update to an existing booking is verified")
    public void bookingEntryIsPatched() {
        response.then().body("firstname", hasToString("Jane"));
    }

    @Step("Given booking ID is deleted")
    public void bookingIsDeleted() {
        String path = "/booking/" + createdBookingID;
        String authToken = BookingHelper.token();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .addCookie("token", authToken)
                .setContentType(ContentType.JSON)
                .build();
        response = SerenityRest.given(requestSpec).delete(path);
    }

    public Integer fetchCreatedBookingID() {
        return createdBookingID;
    }

}
