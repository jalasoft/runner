package cz.jalasoft.runner.domain.model.runner;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/12/15.
 */
public final class Name {

    private String firstName;
    private String lastName;

    Name(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    protected Name() {}

    private void setFirstName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of a runner must not be null.");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name of a runner must not be empty.");
        }
        this.firstName = name.trim();
    }

    private String getFirstName() {
        return firstName;
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

    private String getLastName() {
        return lastName;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(getClass().equals(obj.getClass()))) {
            return false;
        }

        Name that = (Name) obj;

        if (!this.firstName().equals(that.firstName())) {
            return false;
        }

        return this.lastName().equals(that.lastName());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + firstName().hashCode();
        result = result * 37 + lastName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Name[")
                .append(firstName())
                .append(" ")
                .append(lastName())
                .append("]")
                .toString();
    }
}
