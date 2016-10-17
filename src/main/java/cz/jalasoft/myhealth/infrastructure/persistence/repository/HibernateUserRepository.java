package cz.jalasoft.myhealth.infrastructure.persistence.repository;

import cz.jalasoft.myhealth.domain.model.user.*;
import cz.jalasoft.myhealth.infrastructure.persistence.SessionHolder;
import org.hibernate.Query;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
public final class HibernateUserRepository implements UserRepository {

    private final SessionHolder sessionHolder;

    public HibernateUserRepository(SessionHolder sessionProvider) {
        if (sessionProvider == null) {
            throw new IllegalArgumentException("SessionHolder must not be null");
        }

        this.sessionHolder = sessionProvider;
    }

    /*
    @Override
    public boolean contains(Email email) {
        SQLQuery query = sessionHolder.session().createSQLQuery("select count(*) from user where email = ?");

        query.setString(0, email.address());
        BigInteger count = (BigInteger) query.uniqueResult();

        return count.intValue() > 0;
    }*/
    @Override
    public Optional<User> byId(UserId id) {
        Query query = sessionHolder.session().createQuery("from cz.jalasoft.myhealth.domain.model.user.User as u where u.id.uuid = ?");
        query.setParameter(0, id.uuid());

        User user = (User) query.uniqueResult();

        if (user == null) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public Optional<User> byEmail(Email email) {
        Query query = sessionHolder.session().createQuery("from cz.jalasoft.myhealth.domain.model.user.User as r where r.email.address = ?");

        query.setParameter(0, email.address());
        User result = (User) query.uniqueResult();

        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public void remove(UserId id) {
        Optional<User> maybeUser = byId(id);

        if (!maybeUser.isPresent()) {
            throw new IllegalArgumentException("No user with id " + id.uuid() + " present");
        }

        sessionHolder.session().delete(maybeUser.get());
    }

    @Override
    public User add(String emailAddress, String firstName, String lastName, LocalDate birthDay) {
        UserId id = nextId();
        Email email = new Email(emailAddress);
        PersonalInformation personalInfo = new PersonalInformation(firstName, lastName, birthDay);

        User user = new User(id, email, personalInfo);
        sessionHolder.session().save(user);

        return user;
    }

    private UserId nextId() {
        return new UserId(UUID.randomUUID());
    }



}
