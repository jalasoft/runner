package cz.jalasoft.runner.infrastructure.endpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/25/15.
 */
public class RunsResource extends ResourceSupport {

    private Collection<RunResource> runs;

    @JsonCreator
    public RunsResource(
            @JsonProperty(value = "runs", required = true)
            Collection<RunResource> runs) {
        this.runs = runs;
    }

    @JsonProperty(value = "runs")
    public Collection<RunResource> runs() {
        return runs;
    }

}
