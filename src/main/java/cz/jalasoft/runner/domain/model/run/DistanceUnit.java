package cz.jalasoft.runner.domain.model.run;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public enum DistanceUnit {

    KM(0);

    DistanceUnit(int id) {
        this.id = id;
    }

    private final int id;

    public int id() {
        return id;
    }

    //----------------------------------------
    //STATIC SCOPE
    //----------------------------------------

    public static DistanceUnit fromId(int id) {
        if (id == 0) {
            return KM;
        }
        throw new IllegalArgumentException("Unknown DistanceUnit id: " + id);
    }
}
