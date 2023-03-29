package JavaProject.Repository;

import JavaProject.Domain.Referee;

public interface IRefereeRepository extends IRepository<Integer, Referee> {

    Referee findByEmail(String email);
}
