package cz.jalasoft.runner;

import cz.jalasoft.runner.application.RunnerApplicationService;
import cz.jalasoft.runner.application.exception.NoSuchRunnerException;
import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import cz.jalasoft.runner.domain.model.run.Run;
import cz.jalasoft.runner.domain.model.runner.Runner;
import cz.jalasoft.runner.infrastructure.persistence.DatabaseInitializer;
import cz.jalasoft.runner.support.RunExpectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;

import static cz.jalasoft.runner.domain.model.run.Distance.ofKilometers;
import static cz.jalasoft.runner.domain.model.run.Distance.ofMeters;
import static cz.jalasoft.runner.support.RunsMatcher.has;
import static java.time.Duration.ofMinutes;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
@ContextConfiguration(
        classes = Config.class
)
public class RunnerApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RunnerApplicationService service;

    @Autowired
    private DatabaseInitializer dbInitializer;

    protected RunnerApplicationService service() {
        return service;
    }

    @BeforeMethod
    public void initTest() {
        dbInitializer.initialize();
    }

    @Test(expectedExceptions = {NoSuchRunnerException.class})
    public void anAttemptToObtainNotExistingRunnerThrowsException() throws NoSuchRunnerException {
       service().getRunner("Honzales");
    }

    @Test
    public void registersNewRunnerSuccessfully() throws RunnerAlreadyExistsException {

        Runner runner = service().registerRunner("Honzales", "Jan", "Lastovicka", of(1983, 11, 11));

        assertNotNull(runner);
        assertEquals("Honzales", runner.nickname());
        assertEquals("Jan", runner.name().firstName());
        assertEquals("Lastovicka", runner.name().lastName());
        assertEquals(of(1983, 11, 11), runner.birthday());
    }

    @Test
    public void registersAndUnregistersRunner() throws RunnerAlreadyExistsException, NoSuchRunnerException {

        service().registerRunner("Honzales", "Jan", "Lastovicka", of(1983, 11, 11));

        Runner runner = service().getRunner("Honzales");
        assertNotNull(runner);
        assertEquals("Honzales", runner.nickname());
        assertEquals("Jan", runner.name().firstName());
        assertEquals("Lastovicka", runner.name().lastName());
        assertEquals(of(1983, 11, 11), runner.birthday());

        service().unregistersRunner("Honzales");

        try {
            service().getRunner("Honzales");
            throw new AssertionError("Runner Honzales still exists.");
        }  catch (NoSuchRunnerException exc) {
            //expected
        }
    }

    @Test
    public void insertedRunsForRunnerAreRetrieved() throws RunnerAlreadyExistsException, NoSuchRunnerException {

        service().registerRunner("Honzales", "Jan", "Lastovicka", of(1983, 11, 11));
        service().registerRunner("Prdales", "Tonda", "Ponozka", of(2000, 3, 4));

        service().insertRun("Honzales", now().minusDays(2), ofKilometers(5), ofMinutes(40));
        service().insertRun("Honzales", now(), ofKilometers(4), ofMinutes(25));
        service().insertRun("Prdales", now(), ofKilometers(7), ofMinutes(50));

        Collection<Run> runs = service().getRuns("Honzales");
        assertEquals(2, runs.size());

        assertThat(runs, has(RunExpectation.run()
                .withNickname("Honzales")
                .withDate(now())
                .withDistance(ofKilometers(4))
                .withDuration(ofMinutes(25))));

        assertThat(runs, has(RunExpectation.run()
                .withNickname("Honzales")
                .withDate(now().minusDays(2))
                .withDistance(ofKilometers(5))
                .withDuration(ofMinutes(40))));
    }

    @Test(expectedExceptions = {NoSuchRunnerException.class})
    public void anExceptionIsThrownByQueringRunnerPRopertiesAfterItIsUnregistered() throws RunnerAlreadyExistsException, NoSuchRunnerException {

        service().registerRunner("Honzales", "Jan", "Lastovicka", of(1983, 11, 11));

        service().insertRun("Honzales", now().minusDays(1), ofKilometers(5.23), ofMinutes(20));
        service().insertRun("Honzales", now(), ofMeters(5434), ofMinutes(45));

        service().unregistersRunner("Honzales");

        service().getRuns("Honzales");
    }
}
