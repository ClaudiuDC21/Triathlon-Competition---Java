package JavaProject.Domain;

import java.util.Objects;

public class Contest extends Entity<Integer>{

    private String type;
    private int points;
    private int participantID;

    public Contest(String type, int points, int participantID) {
        this.type = type;
        this.points = points;
        this.participantID = participantID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getPoints(), getParticipantID());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Contest))
            return false;
        Contest that = (Contest) obj;
        return getType().equals(that.getType());
    }

    @Override
    public String toString() {
        return "Contest{" +
                "participantID = " + participantID + '\'' +
                ", type = " + type +'\'' +
                ". points = " + points + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getParticipantID() {
        return participantID;
    }

    public void setParticipantID(int participantID) {
        this.participantID = participantID;
    }
}
