package booker.auth;

import booker.BaseBookerTest;
import booker.util.BookingSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AuthenticationTest extends BaseBookerTest {

    @Steps
    BookingSteps steps;

    @Test
    public void canGenerateAuthToken() {
        steps.givenCredentials("password123", "admin");
        steps.responseIsSuccessful();
        steps.authTokenIsGenerated();
    }

    @Test
    public void cannotGenerateAuthToken() {
        steps.givenCredentials("wrong_pass", "wrong_login");
        steps.responseIsUnauthorized();
    }
}
