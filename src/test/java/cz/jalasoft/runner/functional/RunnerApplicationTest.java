package cz.jalasoft.runner.functional;

import cz.jalasoft.myhealth.application.MyHealthApplicationService;
import cz.jalasoft.myhealth.application.exception.NoSuchUserException;
import cz.jalasoft.myhealth.application.exception.UserAlreadyExistsException;
import cz.jalasoft.myhealth.domain.model.user.Email;
import cz.jalasoft.myhealth.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static java.time.LocalDate.of;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
@ContextConfiguration(
        classes = TestConfig.class
)
@ActiveProfiles("dev")
public class RunnerApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyHealthApplicationService service;

    protected MyHealthApplicationService service() {
        return service;
    }

    @Test(expectedExceptions = {NoSuchUserException.class})
    public void anAttemptToObtainNotExistingRunnerThrowsException() throws NoSuchUserException {
       service().getUser("Honzales@prdel.cz");
    }

    @Test
    public void registersNewRunnerSuccessfully() throws UserAlreadyExistsException {

        User user = service().registerUser("Honzales@prdel.cz", "Jan", "Lastovicka", of(1983, 11, 11));

        assertNotNull(user);
        assertEquals(new Email("Honzales@prdel.cz"), user.email());
        assertEquals("Jan", user.firstName());
        assertEquals("Lastovicka", user.lastName());
        assertEquals(of(1983, 11, 11), user.birthDay());
    }

    @Test
    public void registersAndUnregistersRunner() throws UserAlreadyExistsException, NoSuchUserException {

        service().registerUser("honzales@kolin.cz", "Jan", "Lastovicka", of(1983, 11, 11));

        User user = service().getUser("honzales@kolin.cz");
        assertNotNull(user);
        assertEquals("honzales@kolin.cz", user.email().address());
        assertEquals("Jan", user.firstName());
        assertEquals("Lastovicka", user.lastName());
        assertEquals(of(1983, 11, 11), user.birthDay());

        service().unregistersUser("honzales@kolin.cz");

        try {
            service().getUser("honzales@kolin.cz");
            throw new AssertionError("User honzales@kolin still exists.");
        }  catch (NoSuchUserException exc) {
            //expected
        }
    }
/*
    @Test
    public void insertedRunsForRunnerAreRetrieved() throws UserAlreadyExistsException, NoSuchUserException {

        service().registerRunner("Honzales", "Jan", "Lastovicka", of(1983, 11, 11));
        service().registerRunner("Prdales", "Tonda", "Ponozka", of(2000, 3, 4));

        service().insertRun("Honzales", now().minusDays(2).format(ISO_DATE), 5, 40);
        service().insertRun("Honzales", now().format(ISO_DATE), 4, 25);
        service().insertRun("Prdales", now().format(ISO_DATE), 7, 50);

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

    @Test(expectedExceptions = {NoSuchUserException.class})
    public void anExceptionIsThrownByQueringRunnerPRopertiesAfterItIsUnregistered() throws UserAlreadyExistsException, NoSuchUserException {

        service().registerRunner("Honzales", "Jan", "Lastovicka", of(1983, 11, 11));

        service().insertRun("Honzales", now().minusDays(1).format(ISO_DATE), 5.23, 20);
        service().insertRun("Honzales", now().format(ISO_DATE), 5434, 45);

        service().unregistersRunner("Honzales");

        service().getRuns("Honzales");
    }*/
}
