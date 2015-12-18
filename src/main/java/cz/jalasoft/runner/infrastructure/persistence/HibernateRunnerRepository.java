package cz.jalasoft.runner.infrastructure.persistence;

import cz.jalasoft.runner.domain.model.runner.Runner;
import cz.jalasoft.runner.domain.model.runner.RunnerRepository;
import cz.jalasoft.runner.infrastructure.SessionProvider;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
public final class HibernateRunnerRepository implements RunnerRepository {

    private final SessionProvider sessionProvider;

    public HibernateRunnerRepository(SessionProvider sessionProvider) {
        if (sessionProvider == null) {
            throw new IllegalArgumentException("SessionProvider must not be null");
        }

        this.sessionProvider = sessionProvider;
    }

    @Override
    public void add(Runner runner) {
        sessionProvider.session().save(runner);
    }

    @Override
    public void remove(String nickname) {
       //TODO
    }

    @Override
    public boolean has(String nickname) {
        SQLQuery query = sessionProvider.session().createSQLQuery("select count(*) from runner where nickname = ?");

        query.setString(0, nickname);
        BigInteger count = (BigInteger) query.uniqueResult();

        return count.intValue() > 0;
    }

    @Override
    public Runner ofNickname(String nickname) {
        Query query = sessionProvider.session().createQuery("from cz.jalasoft.runner.domain.model.runner.Runner as r where r.nickname = ?");

        query.setParameter(0, nickname);
        Runner result = (Runner) query.uniqueResult();

        if (result == null) {
            throw new IllegalArgumentException("No runner of nickname '" + nickname + "' has been found.");
        }
        return result;
    }

    @Override
    public Collection<Runner> all() {
        Query query = sessionProvider.session().createQuery("from cz.jalasoft.runner.domain.model.runner.Runner");
        Collection<Runner> result = (Collection<Runner>) query.list();
        return result;
    }
}
