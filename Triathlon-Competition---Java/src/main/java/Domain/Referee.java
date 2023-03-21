package Domain;

import java.util.Objects;

public class Referee extends Entity<Integer>{
    String firstName;
    String lastName;
    ContestType type;
    String email;
    String password;

    public Referee(String firstName, String lastName, ContestType type, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.email = email;
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer integer) {
        super.setId(integer);
    }

    @Override
    public int hashCode()  {
        return Objects.hash(getFirstName(),getLastName(), getEmail(), getType(), getPassword());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (!(obj instanceof Participant))
            return false;
        Referee referee = (Referee) obj;
        return getFirstName().equals(referee.getFirstName()) &&
                getLastName().equals(referee.getLastName()) &&
                getType().equals(referee.getType()) &&
                getEmail().equals(referee.getEmail()) &&
                getPassword().equals(referee.getPassword());
    }

    @Override
    public String toString() {
        return "Referee{" +
                "id = " + id + '\'' +
                ", firstName = " + firstName + '\'' +
                ", lastName = " + lastName + '\'' +
                ", type = " + type + '\'' +
                ", email = " + email + '\'' +
                ", password = " + password + '\'' +
                '}';
    }

    public ContestType getType() {
        return type;
    }

    public void setType(ContestType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

