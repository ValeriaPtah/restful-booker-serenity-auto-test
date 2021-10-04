package booker.auth;

import booker.BaseBookerTest;
import booker.steps.AuthenticationSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AuthenticationTest extends BaseBookerTest {

    @Steps
    AuthenticationSteps authSteps;

    @Test
    public void canGenerateAuthToken() {
        authSteps.givenCredentials("password123", "admin");
        authSteps.responseIsSuccessful();
        authSteps.authTokenIsGenerated();
    }

    @Test
    public void cannotGenerateAuthToken() {
        authSteps.givenCredentials("wrong_pass", "wrong_login");
        authSteps.responseIsUnauthorized();
    }
}
