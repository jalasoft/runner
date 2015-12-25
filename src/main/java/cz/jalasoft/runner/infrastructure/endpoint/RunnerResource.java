package cz.jalasoft.runner.infrastructure.endpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
public class RunnerResource extends ResourceSupport {

    private String nickname;
    private String firstName;
    private String lastName;
    private String birthday;

    @JsonCreator
    public RunnerResource(
            @JsonProperty
            String nickname,
            @JsonProperty
            String firstName,
            @JsonProperty
            String lastName,
            @JsonProperty
            String birthday) {

        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    @JsonProperty
    public String getNickname() {
        return nickname;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public String getBirthday() {
        return birthday;
    }
}
