package mapping;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InaccessibilityImplTest {
  @Test
    void shouldHaveCorrectСalсulationNeg(){

        LocalDateTime date1 = LocalDateTime.of(2017,6,14,16,47,12);
        LocalDateTime date2 = LocalDateTime.of(2017,6,14,16,48,12);
        AccessLog[] accessLog = {new AccessLog(date1,"200",43F),new AccessLog(date2,"200",40F)};
        InaccessibilityImpl inaccessibilityImpl = new InaccessibilityImpl(accessLog,90F,44F);
        assertEquals(Arrays.stream(inaccessibilityImpl.getAllInaccessibilityValue()).filter(Objects::nonNull).count(),0);

    }

    @Test
    void shouldHaveCorrectСalсulationPos(){

        LocalDateTime date1 = LocalDateTime.of(2017,6,14,16,47,12);
        LocalDateTime date2 = LocalDateTime.of(2017,6,14,16,48,12);
        AccessLog[] accessLog = {new AccessLog(date1,"200",43F),new AccessLog(date2,"200",40F)};
        InaccessibilityImpl inaccessibilityImpl = new InaccessibilityImpl(accessLog,90F,42F);
        assertEquals(Arrays.stream(inaccessibilityImpl.getAllInaccessibilityValue()).filter(Objects::nonNull).count(),1);

    }
}