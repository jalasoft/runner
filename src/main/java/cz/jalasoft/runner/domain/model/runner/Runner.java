package cz.jalasoft.runner.domain.model.runner;

import cz.jalasoft.myhealth.domain.model.DomainEntity;

import java.time.LocalDate;

import static java.time.Duration.between;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public class Runner extends DomainEntity {

    private String nickname;
    private Name name;
    private LocalDate birthday;

    public Runner(String nickname, String firstName, String lastName, LocalDate birthday) {
        setNickname(nickname);
        setName(firstName, lastName);
        setBirthday(birthday);
    }

    protected Runner() {

    }

    private void setNickname(String nickname) {
        if (this.nickname != null) {
            throw new IllegalStateException("Nickname cannot be set more than once.");
        }

        if (nickname == null) {
            throw new IllegalArgumentException("Nickname of a runner must not be null.");
        }

        if (nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname of a runner must not be empty.");
        }

        this.nickname = nickname;
    }

    private String getNickname() {
        return nickname;
    }

    private void setName(String firstName, String lastName) {
        this.name = new Name(firstName, lastName);
    }

    private void setBirthday(LocalDate birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException("Birthday of a runner must not be null.");
        }
        if (now().isBefore(birthday)) {
            throw new IllegalArgumentException("Birthday of a runner must be past date,");
        }
        this.birthday = birthday;
    }

    public String nickname() {
        return nickname;
    }

    public Name name() {
        return name;
    }

    public LocalDate birthday() {
        return birthday;
    }

    public int age() {
        long result = between(birthday(), now()).get(YEARS);
        return (int) result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Runner)) {
            return false;
        }

        Runner that = (Runner) obj;

        return this.nickname().equals(that.nickname());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + nickname().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Runner[")
                .append(nickname())
                .append(",")
                .append(name())
                .append(",birthday=")
                .append(birthday())
                .append("]")
                .toString();
    }
}
