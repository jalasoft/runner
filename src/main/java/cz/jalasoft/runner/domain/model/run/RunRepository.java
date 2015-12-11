package cz.jalasoft.runner.domain.model.run;

import cz.jalasoft.runner.domain.model.runner.RunnerId;

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

    Iterable<Run> in(RunnerId runnerId, TimeSpan timeSpan);

    Iterable<Run> all(RunnerId runnerId);
}
