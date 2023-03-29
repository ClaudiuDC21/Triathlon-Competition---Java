package JavaProject.Domain;

import java.util.Comparator;
import java.util.Objects;

public class Participant extends Entity<Integer> implements Comparator<Participant> {

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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFullNameReversed() {
        return this.lastName + " " + this.lastName;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(),getLastName(),getNumberOfPoints());
    }

    @Override
    public int compare(Participant o1, Participant o2) {
        return o1.lastName.compareTo(o2.lastName);
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
