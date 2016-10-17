package cz.jalasoft.runner.unit;

import org.testng.annotations.Test;

import static cz.jalasoft.myhealth.domain.model.run.Distance.ofKilometers;
import static cz.jalasoft.myhealth.domain.model.run.Distance.ofMeters;
import static cz.jalasoft.myhealth.domain.model.run.DistanceUnit.KILOMETER;
import static cz.jalasoft.myhealth.domain.model.run.DistanceUnit.METER;
import static org.testng.Assert.assertEquals;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/20/15.
 */
public class DistanceTest {

    @Test
    public void kilometersAreCorrectlyConvertedToMeters() {
        double result = ofKilometers(4.45).in(METER);

        assertEquals(4450.0, result);
    }

    @Test
    public void metersAreCorrectlyConvertedToKilometers() {
        double kilometers = ofMeters(2343).in(KILOMETER);
        assertEquals(2.343, kilometers);
    }

    @Test
    public void metersAreTheSameAsConvertedToMeters() {
        double meters = ofMeters(3356).in(METER);
        assertEquals(3356.0, meters);
    }


}
