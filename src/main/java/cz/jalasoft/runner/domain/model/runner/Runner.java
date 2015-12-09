package cz.jalasoft.runner.domain.model.runner;

import cz.jalasoft.runner.domain.model.DomainEntity;

import java.time.LocalDate;

import static java.time.Duration.between;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
public class Runner extends DomainEntity {

    private RunnerId id;

    private String name;
    private String surname;
    private LocalDate birthday;

    public Runner(RunnerId id, String name, String surname, LocalDate birthday) {
        setId(id);
        setName(name);
        setSurname(surname);
        setBirthday(birthday);
    }

    protected Runner() {

    }

    private void setId(RunnerId id) {
        if (id == null) {
            throw new IllegalArgumentException("Id of runner must not be null.");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null.");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        this.name = name.trim();
    }

    private void setSurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("Surname must not be null.");
        }
        if (surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname must not be empty.");
        }
        this.surname = surname.trim();
    }

    private void setBirthday(LocalDate birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException("Birthday must not be null.");
        }
        if (now().isBefore(birthday)) {
            throw new IllegalArgumentException("Birthday must be past date,");
        }
        this.birthday = birthday;
    }

    public RunnerId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
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

        return this.id().equals(that.id());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + id().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Runner[")
                .append("name:")
                .append(name())
                .append(",surname:")
                .append(surname())
                .append("]")
                .toString();
    }
}
