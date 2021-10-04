package booker.booking;

import booker.BaseBookerTest;
import booker.steps.BookingSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class BookingServiceTest extends BaseBookerTest {

    @Steps
    BookingSteps bookingSteps;

    @Test
    public void canGetAllBookings() {
        bookingSteps.whenRequestingListOfBookings();
        bookingSteps.responseIsSuccessful();
        bookingSteps.listOfBookingsIsReturned();
    }
}
