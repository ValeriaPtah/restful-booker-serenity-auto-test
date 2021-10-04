package booker.steps;

import booker.BaseBookerTest;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

public class SharedSteps extends BaseBookerTest {

    @Step("Response is successful")
    public void responseIsSuccessful(Response response) {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Step("Response is unauthorized")
    public void responseIsUnauthorized(Response response) {
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
