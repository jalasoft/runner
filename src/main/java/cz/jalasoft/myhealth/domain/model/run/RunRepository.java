package cz.jalasoft.myhealth.domain.model.run;

import cz.jalasoft.myhealth.domain.model.user.UserId;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/9/15.
 */
public interface RunRepository {

    Run add(UserId userId, LocalDate when, Distance distance, Duration duration);

    Collection<Run> between(UserId userId, TimeSpan timeSpan);

    Collection<Run> all(UserId userId);
}
