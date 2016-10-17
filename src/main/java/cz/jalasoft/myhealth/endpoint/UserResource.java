package cz.jalasoft.myhealth.endpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
public class UserResource extends ResourceSupport {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String birthday;

    @JsonCreator
    public UserResource(
            @JsonProperty("emailAddress")
            String emailAddress,
            @JsonProperty("first-name")
            String firstName,
            @JsonProperty("last-name")
            String lastName,
            @JsonProperty("birthday")
            String birthday) {

        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    @JsonProperty("emailAddress")
    public String getEmailAddress() {
        return emailAddress;
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
