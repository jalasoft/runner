package cz.jalasoft.myhealth.infrastructure.persistence;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
public final class SessionHolder {

    private final ThreadLocal<Session> sessions = new ThreadLocal<>();

    public Session session() {
        return sessions.get();
    }

    void setSession(Session session) {
        sessions.set(session);
    }

    void removeSession() {
        sessions.remove();
    }
}
