package cz.jalasoft.myhealth.domain.model.user;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-09-28.
 */
public interface UserRepository {

    Optional<User> byEmail(Email email);

    Optional<User> byId(UserId id);

    void remove(UserId id);

    User add(String email, String firstName, String lastName, LocalDate birthDay);
}
