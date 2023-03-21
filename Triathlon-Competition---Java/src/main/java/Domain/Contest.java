package Domain;

import java.util.Objects;

public class Contest extends Entity<Integer>{

    private ContestType type;
    private int points;
    private int participantID;

    public Contest(ContestType type, int points, int participantID) {
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

    public ContestType getType() {
        return type;
    }

    public void setType(ContestType type) {
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
