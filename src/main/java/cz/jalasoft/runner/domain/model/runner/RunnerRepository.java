package cz.jalasoft.runner.domain.model.runner;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public interface RunnerRepository {

    default RunnerId nextIdentity() {
        String uuid = UUID.randomUUID().toString();
        return new RunnerId(uuid);
    }

    Runner createRunner(String name, String surname, LocalDate birthdate);


    void addRunner(Runner runner);

    Runner ofId(RunnerId id);

    Collection<Runner> all();
}
