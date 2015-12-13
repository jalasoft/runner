package cz.jalasoft.runner.infrastructure.persistence;

import cz.jalasoft.runner.domain.model.runner.Runner;
import cz.jalasoft.runner.domain.model.runner.RunnerId;
import cz.jalasoft.runner.domain.model.runner.RunnerRepository;
import cz.jalasoft.runner.infrastructure.SessionProvider;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
@Repository
public class HibernateRunnerRepository implements RunnerRepository {

    @Autowired
    private SessionProvider sessionProvider;

    @Override
    public void add(Runner runner) {
        sessionProvider.session().save(runner);
    }

    @Override
    public void remove(RunnerId runnerId) {
       //TODO
    }

    @Override
    public Runner ofNickname(String nickname) {
        Query query = sessionProvider.session().createQuery("from cz.jalasoft.runner.domain.model.runner.Runner as r where r.nickname = ?");

        query.setParameter(0, nickname);
        Runner result = (Runner) query.uniqueResult();

        return result;
    }

    @Override
    public Collection<Runner> all() {
        Query query = sessionProvider.session().createQuery("from cz.jalasoft.runner.domain.model.runner.Runner");
        Collection<Runner> result = (Collection<Runner>) query.list();
        return result;
    }
}
