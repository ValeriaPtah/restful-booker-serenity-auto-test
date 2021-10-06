package booker.booking;

import booker.BaseBookerTest;
import booker.util.BookingHelper;
import booker.util.BookingSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class NegativeBookingServiceTest extends BaseBookerTest {

    @Steps
    BookingSteps steps;

    @Test
    public void cannotPatchWithoutToken() {
        Integer bookingID = BookingHelper.randomBookingID();
        steps.givenCorrectBookingIdIsProvided(bookingID);
        steps.patchWithoutToken(bookingID);
        steps.responseIsForbidden();
    }

    @Test
    public void cannotUpdateWithoutToken() {
        Integer bookingID = BookingHelper.randomBookingID();
        steps.givenCorrectBookingIdIsProvided(bookingID);
        steps.updateWithoutToken(bookingID);
        steps.responseIsForbidden();
    }

}
