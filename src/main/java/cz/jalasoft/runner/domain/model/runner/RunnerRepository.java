package cz.jalasoft.runner.domain.model.runner;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public interface RunnerRepository {

    void add(Runner runner);

    void remove(String nickname);

    Runner ofNickname(String nickname);

    boolean has(String nickname);

    Collection<Runner> all();
}
