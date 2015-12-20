package cz.jalasoft.runner;

import cz.jalasoft.runner.domain.model.run.Distance;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;
import static cz.jalasoft.runner.domain.model.run.DistanceUnit.*;

import static cz.jalasoft.runner.domain.model.run.Distance.*;

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
