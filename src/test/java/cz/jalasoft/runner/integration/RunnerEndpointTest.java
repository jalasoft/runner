package cz.jalasoft.runner.integration;

import cz.jalasoft.runner.infrastructure.endpoint.RunnerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static cz.jalasoft.runner.integration.Util.serialize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/25/15.
 */
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class RunnerEndpointTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeMethod
    public void initMock() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test(enabled = false)
    public void registersNewRunner() throws Exception {
        RunnerResource newRunner = new RunnerResource("Honzales", "Honza", "Lastovicka", "1983-11-11");

        mockMvc.perform(
                post("/runner")
                        .contentType(APPLICATION_JSON)
                        .content(serialize(newRunner)
                        )
        ).andExpect(status().isCreated());
    }
}
