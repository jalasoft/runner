package cz.jalasoft.runner.domain.model.run;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/9/15.
 */
public interface RunRepository {

    default RunId nextId() {
        UUID randonUUID = UUID.randomUUID();
        return new RunId(randonUUID.toString());
    }

    void add(Run run);

    Collection<Run> in(String nickname, TimeSpan timeSpan);

    Collection<Run> all(String nickname);
}
