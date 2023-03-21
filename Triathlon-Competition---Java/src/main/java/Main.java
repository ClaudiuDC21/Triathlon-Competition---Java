import Domain.ContestType;
import Domain.Participant;
import Domain.Contest;
import Domain.Referee;
import Repository.DatabaseRepo.ContestDBRepository;
import Repository.DatabaseRepo.ParticipantDBRepository;
import Repository.DatabaseRepo.RefereeDBRepository;
import Repository.IContestRepository;
import Repository.IParticipantRepository;
import Repository.IRefereeRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
       System.out.println("mergee");

        Properties properties=new Properties();
        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
            System.out.println(new File(".").getAbsolutePath());
        }

        for(int index = 1;index < 99 ;index ++) {
            IRefereeRepository refereeRepository = new RefereeDBRepository(properties);
            refereeRepository.delete(index);

            IParticipantRepository participantRepository = new ParticipantDBRepository(properties);
            participantRepository.delete(index);

            IContestRepository contestRepository = new ContestDBRepository(properties);
            contestRepository.delete(index);

        }

        //Referee

        IRefereeRepository refereeRepository = new RefereeDBRepository(properties);
        refereeRepository.save(new Referee("Istvan","Kovacs", ContestType.SWIMMING,"istvan.kovacs@gmail.com","arbitru"));
        for(Referee referee: refereeRepository.findAll())
            System.out.println(referee);

        //participant
        IParticipantRepository participantRepository = new ParticipantDBRepository(properties);
        participantRepository.save(new Participant("Dascalu","Claudiu", 0));
        for(Participant participant: participantRepository.findAll())
            System.out.println(participant);


        //Contest
        IContestRepository contestRepository = new ContestDBRepository(properties);
        contestRepository.save(new Contest(ContestType.RUNNING, 20, 1));
        for(Contest contest: contestRepository.findAll())
            System.out.println(contest);

    }
}
