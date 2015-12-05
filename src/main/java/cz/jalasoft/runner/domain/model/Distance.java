package cz.jalasoft.runner.domain.model;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class Distance {

    public static Distance ofKilemeters(int kilemeters) {
        return new Distance(kilemeters, DistanceUnit.KM);
    }

    private final int value;
    private final DistanceUnit unit;

    Distance(int value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public int value() {
        return value;
    }

    public DistanceUnit unit() {
        return unit;
    }

}
