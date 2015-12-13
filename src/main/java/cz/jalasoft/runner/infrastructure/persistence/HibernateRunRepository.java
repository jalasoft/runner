package cz.jalasoft.runner.infrastructure.persistence;

import cz.jalasoft.runner.domain.model.run.Run;
import cz.jalasoft.runner.domain.model.run.RunRepository;
import cz.jalasoft.runner.domain.model.run.TimeSpan;
import cz.jalasoft.runner.domain.model.runner.RunnerId;
import cz.jalasoft.runner.infrastructure.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
@Repository
public class HibernateRunRepository implements RunRepository {

    @Autowired
    private SessionProvider sessionProvider;

    @Override
    public void add(Run run) {

    }

    @Override
    public Collection<Run> in(RunnerId runnerId, TimeSpan timeSpan) {
        return null;
    }

    @Override
    public Collection<Run> all(RunnerId runnerId) {
        return null;
    }
}
