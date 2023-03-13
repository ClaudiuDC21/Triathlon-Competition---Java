package Domain;

import java.util.Objects;

public class Contest extends Entity<Integer>{

    String sport;
    String referee;

    public Contest(String sport, String referee) {
        this.sport = sport;
        this.referee = referee;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReferee(),getSport());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Contest))
            return false;
        Contest that = (Contest) obj;
        return getReferee().equals(that.getReferee()) &&
                getSport().equals(that.getSport());
    }

    @Override
    public String toString() {
        return "Contest{" +
                "sport = " + sport + '\'' +
                "referee = " + referee +
                '}'
                ;
    }
}
