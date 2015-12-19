package cz.jalasoft.runner.domain.model.run;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class Distance {

    public static Distance ofKilometers(float kilometers) {
        return new Distance(kilometers, DistanceUnit.KM);
    }

    private float value;
    private DistanceUnit unit;

    Distance(float value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    protected Distance() {
    }

    private float getValue() {
        return value;
    }

    private void setValue(float value) {
        this.value = value;
    }

    private DistanceUnit getUnit() {
        return unit;
    }

    public void setUnit(DistanceUnit unit) {
        this.unit = unit;
    }

    public float value() {
        return value;
    }

    public DistanceUnit unit() {
        return unit;
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

        if (!this.unit().equals(that.unit())) {
            return false;
        }

        return this.value() == that.value();
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + unit().hashCode();
        result = result * 37 + Float.floatToIntBits(value());

        return result;
    }
}
