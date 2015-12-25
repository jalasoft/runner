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
            @JsonProperty("nickname")
            String nickname,
            @JsonProperty("first-name")
            String firstName,
            @JsonProperty("last-name")
            String lastName,
            @JsonProperty("birthday")
            String birthday) {

        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("first-name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last-name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("birthday")
    public String getBirthday() {
        return birthday;
    }
}
