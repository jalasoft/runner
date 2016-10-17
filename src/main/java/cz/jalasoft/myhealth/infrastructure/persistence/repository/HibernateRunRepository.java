package cz.jalasoft.myhealth.infrastructure.persistence.repository;

import cz.jalasoft.myhealth.domain.model.run.*;
import cz.jalasoft.myhealth.domain.model.user.UserId;
import cz.jalasoft.myhealth.infrastructure.persistence.SessionHolder;
import org.hibernate.Query;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
public class HibernateRunRepository implements RunRepository {

    private final SessionHolder sessionHolder;

    public HibernateRunRepository(SessionHolder sessionProvider) {
        if (sessionProvider == null) {
            throw new IllegalArgumentException("SessionHolder must not be null.");
        }

        this.sessionHolder = sessionProvider;
    }

    @Override
    public Run add(UserId userId, LocalDate when, Distance distance, Duration duration) {
        RunId id = nextId();

        Run newRun = new Run(id, userId, when, distance, duration);

        sessionHolder.session().save(newRun);
        return newRun;
    }

    @Override
    public Collection<Run> between(UserId userId, TimeSpan timeSpan) {
        return null;
    }

    @Override
    public Collection<Run> all(UserId userId) {
        Query query = sessionHolder
                .session()
                .createQuery("from cz.jalasoft.myhealth.domain.model.run.Run r where r.userId.uuid = ?");
        query.setParameter(0, userId.uuid());

        Collection<Run> result = query.list();

        return result;
    }

    private RunId nextId() {
        UUID randonUUID = UUID.randomUUID();
        return new RunId(randonUUID);
    }
}
