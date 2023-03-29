package JavaProject.Repository;

import JavaProject.Domain.Contest;

public interface IContestRepository extends IRepository<Integer, Contest> {

    public abstract Contest findByParticipantID(Integer athleteId);
    public abstract Iterable<Contest> findAllWithPoints();

}
