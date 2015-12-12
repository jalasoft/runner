package cz.jalasoft.runner.domain.model.run;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class Distance {

    public static Distance ofKilometers(int kilemeters) {
        return new Distance(kilemeters, DistanceUnit.KM);
    }

    private int value;
    private DistanceUnit unit;

    Distance(int value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    protected Distance() {

    }

    private int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    private String getUnit() {
        return unit.toString();
    }

    public void setUnit(String unit) {
        this.unit = DistanceUnit.valueOf(unit);
    }

    public int value() {
        return value;
    }

    public DistanceUnit unit() {
        return unit;
    }

}
