package Domain;

import java.util.Objects;

public class Competitor extends Entity<Integer> {

    String firstName;
    String lastName;
    int numberOfPoints;
    String sport;

    public Competitor(String firstName, String lastName, int numberOfPoints, String sport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPoints = numberOfPoints;
        this.sport = sport;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(),getLastName(),getNumberOfPoints(),getSport());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (!(obj instanceof Competitor))
            return false;
        Competitor competitor = (Competitor) obj;
        return getFirstName().equals(competitor.getFirstName()) &&
                getLastName().equals(competitor.getLastName()) &&
                getSport().equals(competitor.getSport());
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "firstName = " + firstName + '\'' +
                "lastName = " + lastName + '\'' +
                "numberOfPoints = " + numberOfPoints + '\'' +
                "sport = " + sport +
                '}'
                ;
    }
}
