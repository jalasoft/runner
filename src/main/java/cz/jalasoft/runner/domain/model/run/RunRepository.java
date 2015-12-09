package cz.jalasoft.runner.domain.model.run;

import java.time.Duration;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/9/15.
 */
public interface RunRepository {

    RunId nextId();

    void add(Run run);

    Iterable<Run> last(Duration duration);

    Iterable<Run> all();
}
