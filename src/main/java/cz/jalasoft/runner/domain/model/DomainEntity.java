package cz.jalasoft.runner.domain.model;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/9/15.
 */
public abstract class DomainEntity {

    private String id;

    protected final String getId() {
        return id;
    }

    protected final void setId(String id) {
        this.id = id;
    }
}
