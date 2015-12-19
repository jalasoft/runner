package cz.jalasoft.runner;

import cz.jalasoft.runner.application.RunnerApplicationService;
import cz.jalasoft.runner.application.exception.NoSuchRunnerException;
import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import cz.jalasoft.runner.domain.model.run.Distance;
import cz.jalasoft.runner.domain.model.run.Run;
import cz.jalasoft.runner.domain.model.runner.Runner;
import cz.jalasoft.runner.infrastructure.DatabaseInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;

import java.util.Collection;

import static java.time.Duration.*;
import static java.time.LocalDate.*;
import static org.testng.Assert.*;
import static cz.jalasoft.runner.domain.model.run.Distance.*;

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

        service().insertRun("Honzales", now().minusDays(2), ofKilometers(5), ofMinutes(40));
        service().insertRun("Honzales", now(), ofKilometers(4), ofMinutes(25));

        Collection<Run> runs = service().getRuns("Honzales");
        assertEquals(2, runs.size());
    }
}
