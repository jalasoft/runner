package cz.jalasoft.myhealth.domain.model.run;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public enum DistanceUnit {

    METER {
        @Override
        long toMeters(double value) {
            return (long) value;
        }

        @Override
        double fromMeters(long meters) {
            return (double) meters;
        }
    },
    KILOMETER {
        @Override
        long toMeters(double value) {
            return (long)(value * 1000);
        }

        @Override
        double fromMeters(long meters) {
            double value = (double) meters / 1000;

            double result = (double) (Math.round(value * 1000)) / 1000;
            return result;
        }
    };

    public double to(double value, DistanceUnit unit) {
        long meters = toMeters(value);
        return unit.fromMeters(meters);
    }

    abstract long toMeters(double value);

    abstract double fromMeters(long meters);

}
