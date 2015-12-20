package cz.jalasoft.runner.domain.model.run;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class Distance {

    //---------------------------------------------------------
    //FACTORY METHODS
    //---------------------------------------------------------

    public static Distance ofMeters(long meters) {
        if (meters <= 0) {
            throw new IllegalArgumentException("Meters must be greater than zero.");
        }

        return new Distance(meters);
    }

    public static Distance ofKilometers(double kilometers) {
        if (kilometers <= 0) {
            throw new IllegalArgumentException("Kilometers must be greater than zero.");
        }

        double value = DistanceUnit.KILOMETER.to(kilometers, DistanceUnit.METER);
        return new Distance((long) value);
    }

    //----------------------------------------------------------
    //INSTANCE SCOPE
    //----------------------------------------------------------



    private long valueInMeters;

    Distance(long valueInMeters) {
        this.valueInMeters = valueInMeters;
    }

    protected Distance() {
    }

    private long getValue() {
        return valueInMeters;
    }

    private void setValue(long value) {
        this.valueInMeters = value;
    }

    public double in(DistanceUnit unit) {
        double result = DistanceUnit.METER.to(valueInMeters, unit);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Distance)) {
            return false;
        }

        Distance that = (Distance) obj;

        return this.valueInMeters == that.valueInMeters;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + (int) valueInMeters;

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Distance[")
                .append(valueInMeters)
                .append(" m]")
                .toString();
    }
}
