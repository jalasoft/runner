package cz.jalasoft.runner.domain.model.run;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/9/15.
 */
public final class RunId {

    private final String id;

    RunId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return false;
        }

        if (!(obj instanceof RunId)) {
            return false;
        }

        RunId that = (RunId) obj;

        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("RunId[")
                .append(id)
                .append("]")
                .toString();
    }
}
