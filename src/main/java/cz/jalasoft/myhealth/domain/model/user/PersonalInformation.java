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

    protected PersonalInformation() {
        //persistence
    }

    public PersonalInformation(String firstName, String lastName, LocalDate birthDay) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDay(birthDay);
    }

    private void setFirstName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of a runner must not be null.");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name of a runner must not be empty.");
        }
        this.firstName = name.trim();
    }

    private void setLastName(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("Surname of a runner must not be null.");
        }
        if (surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname of a runner must not be empty.");
        }
        this.lastName = surname.trim();
    }

    private void setBirthDay(LocalDate birthDay) {
        if (birthDay == null) {
            throw new IllegalArgumentException("Birthday must not be null.");
        }

        if (LocalDate.now().isBefore(birthDay)) {
            throw new IllegalArgumentException("Birthday must not be after now.");
        }

        this.birthDay = birthDay;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public LocalDate birthDay() {
        return birthDay;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PersonalInformation)) {
            return false;
        }

        PersonalInformation that = (PersonalInformation) obj;

        if (!(this.birthDay.equals(that.birthDay))) {
            return false;
        }

        if (!(this.lastName.equals(that.lastName))) {
            return false;
        }

        return this.firstName.equals(that.firstName);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + birthDay.hashCode();
        result = result * 37 + lastName.hashCode();
        result = result * 37 + firstName.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("PersonalInfo[")
                .append(firstName)
                .append(" ")
                .append(lastName)
                .append(" born on ")
                .append(birthDay)
                .append("]")
                .toString();
    }
}
