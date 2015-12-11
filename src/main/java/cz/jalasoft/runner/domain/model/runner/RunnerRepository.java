package cz.jalasoft.runner.domain.model.runner;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public interface RunnerRepository {

    default RunnerId nextIdentity() {
        UUID randomUUID = UUID.randomUUID();
        return new RunnerId(randomUUID.toString());
    }

    void add(Runner runner);

    void remove(RunnerId runnerId);

    Runner ofNickname(String nickname);

    Collection<Runner> all();
}
