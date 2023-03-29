package JavaProject.Utils;

import JavaProject.Domain.Referee;
import JavaProject.Repository.DatabaseRepo.ContestDBRepository;
import JavaProject.Repository.DatabaseRepo.ParticipantDBRepository;
import JavaProject.Repository.DatabaseRepo.RefereeDBRepository;
import JavaProject.Repository.IContestRepository;
import JavaProject.Repository.IParticipantRepository;
import JavaProject.Repository.IRefereeRepository;
import JavaProject.Service.TriatlonService;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DependencyProvider {

    private static DependencyProvider instance = null;

    private final IContestRepository contestRepository;
    private final IParticipantRepository participantRepository;
    private final IRefereeRepository refereeRepository;
    private final TriatlonService triatlonService;
    private Referee referee;

    private DependencyProvider() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException ignored) {}
        contestRepository = new ContestDBRepository(properties);
        participantRepository = new ParticipantDBRepository(properties);
        refereeRepository = new RefereeDBRepository(properties);
        triatlonService = new TriatlonService(contestRepository, participantRepository, refereeRepository);
    }

    public TriatlonService getSharedService() {
        return triatlonService;
    }

    public TriatlonService getNewService() {
        return new TriatlonService(contestRepository, participantRepository, refereeRepository);
    }

    public static DependencyProvider getInstance() {
        if (instance == null) instance = new DependencyProvider();
        return instance;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public Referee getReferee() {
        return this.referee;
    }

}
