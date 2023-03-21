package Domain;

import java.util.Objects;

public class Participant extends Entity<Integer> {

    String firstName;
    String lastName;
    int numberOfPoints;

    public Participant(String firstName, String lastName, int numberOfPoints) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPoints = numberOfPoints;
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

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }



    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(),getLastName(),getNumberOfPoints());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (!(obj instanceof Participant))
            return false;
        Participant participant = (Participant) obj;
        return getFirstName().equals(participant.getFirstName()) &&
                getLastName().equals(participant.getLastName()) ;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id = " + id + '\'' +
                ", firstName = " + firstName + '\'' +
                ", lastName = " + lastName + '\'' +
                ", numberOfPoints = " + numberOfPoints +
                '}'
                ;
    }
}
