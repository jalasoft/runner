package cz.jalasoft.myhealth.domain.model.user;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-09-28.
 */
public final class Email {

    private String address;

    protected Email() {
        //persistence
    }

    public Email(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Email address must not be null or empty.");
        }
        this.address = address;
    }

    public String address() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Email)) {
            return false;
        }

        Email that = (Email) obj;

        return this.address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + address.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Email[" + address + "]";
    }
}
