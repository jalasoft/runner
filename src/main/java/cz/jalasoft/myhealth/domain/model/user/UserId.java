package cz.jalasoft.myhealth.domain.model.user;

import java.util.UUID;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-09-29.
 */
public final class UserId {

    private UUID uuid;

    protected UserId() {
        //persistence
    }

    public UserId(UUID value) {
        this.uuid = value;
    }

    public UUID uuid() {
        return uuid;
    }
}
