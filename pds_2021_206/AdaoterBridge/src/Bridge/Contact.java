package Bridge;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private String name;
    private int phoneNum;
    private String email;

    public Contact(String name, int phoneNum, String email) {
        this.name = name;
        this.phoneNum =  phoneNum;
        this.email = email;
    }

    public String toString() {
        return name + ";" + phoneNum + ";" + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return phoneNum == contact.phoneNum && Objects.equals(name, contact.name) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNum, email);
    }

    public String getName() {
        return name;
    }
}
