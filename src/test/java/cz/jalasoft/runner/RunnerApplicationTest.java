package cz.jalasoft.runner;

import cz.jalasoft.runner.application.RunnerApplicationService;
import cz.jalasoft.runner.application.exception.RunnerAlreadyExistsException;
import cz.jalasoft.runner.domain.model.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
@ContextConfiguration(
        classes = Config.class
)
public class RunnerApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void test() throws RunnerAlreadyExistsException {

        RunnerApplicationService service = context.getBean(RunnerApplicationService.class);

        Runner runner = service.registerRunner("Honzales", "Jan", "Lastovicla", LocalDate.of(1983, 11, 11));

        Assert.assertNotNull(runner);
    }
}
