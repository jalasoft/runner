package cz.jalasoft.runner.infrastructure;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
public class SessionProvider {

    private Session currentSession;

    public Session session() {
        return currentSession;
    }

    void setSession(Session session) {
        currentSession = session;
    }

    void removeSession() {
        setSession(null);
    }
}
