package cz.jalasoft.runner.infrastructure.persistence;

import cz.jalasoft.runner.domain.model.run.Run;
import cz.jalasoft.runner.domain.model.run.RunRepository;
import cz.jalasoft.runner.domain.model.run.TimeSpan;
import cz.jalasoft.runner.infrastructure.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
public class HibernateRunRepository implements RunRepository {

    private final SessionProvider sessionProvider;

    public HibernateRunRepository(SessionProvider sessionProvider) {
        if (sessionProvider == null) {
            throw new IllegalArgumentException("SessionProvider must not be null.");
        }

        this.sessionProvider = sessionProvider;
    }

    @Override
    public void add(Run run) {

    }

    @Override
    public Collection<Run> in(String nickname, TimeSpan timeSpan) {
        return null;
    }

    @Override
    public Collection<Run> all(String nickname) {
        return null;
    }
}
