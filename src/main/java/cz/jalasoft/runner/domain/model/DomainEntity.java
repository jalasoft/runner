package cz.jalasoft.runner.domain.model;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/9/15.
 */
public abstract class DomainEntity {

    private String persistenceId;

    private String getPersistenceId() {
        return persistenceId;
    }

    private void setPersistenceId(String persistenceId) {
        this.persistenceId = persistenceId;
    }
}
