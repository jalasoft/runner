package cz.jalasoft.runner.domain.model.runner;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public final class RunnerId {

    private String id;

    RunnerId(String id) {
        this.id = id;
    }

    protected RunnerId() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RunnerId)) {
            return false;
        }

        RunnerId that = (RunnerId) obj;

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
        return new StringBuilder("RunnerId[")
                .append(id)
                .append("]")
                .toString();
    }
}
