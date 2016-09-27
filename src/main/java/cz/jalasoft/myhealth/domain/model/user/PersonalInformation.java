package cz.jalasoft.myhealth.domain.model.user;

import java.time.LocalDate;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-09-27.
 */
public final class PersonalInformation {

    private String firstName;
    private String lastName;
    private LocalDate birthDay;


    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public LocalDate birthDay() {
        return birthDay;
    }

    //----------------------------------------------------------
    //FOR PERSISTENCE MECHANISM
    //----------------------------------------------------------

    private String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private LocalDate getBirthDay() {
        return birthDay;
    }

    private void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
