package cz.jalasoft.myhealth.domain.model.user;

import cz.jalasoft.myhealth.domain.model.DomainEntity;

import java.time.LocalDate;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * ///.,m,,,,nk,kk,k.@since 2016-09-27.
 */
public class User extends DomainEntity {

    private UserId id;
    private Email email;
    private PersonalInformation personalInfo;

    public User(UserId id, Email email, PersonalInformation personalInfo) {
        this.id = id;
        this.email = email;
        this.personalInfo = personalInfo;
    }

    protected User() {}

    public UserId id() {
        return id;
    }

    public String firstName() {

        return personalInfo.firstName();
    }

    public String lastName() {
        return personalInfo.lastName();
    }

    public LocalDate birthDay() {

        return personalInfo.birthDay();
    }
    public Email email() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User that = (User) obj;

        return this.email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + email.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "User[" + email + "]";
    }
}
