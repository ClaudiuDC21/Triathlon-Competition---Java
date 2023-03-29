package JavaProject.Service;

import JavaProject.Domain.Contest;
import JavaProject.Domain.Participant;
import JavaProject.Domain.Referee;
import JavaProject.Domain.Result;
import JavaProject.Repository.IContestRepository;
import JavaProject.Repository.IParticipantRepository;
import JavaProject.Repository.IRefereeRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TriatlonService {
    private final IContestRepository contestRepository;
    private final IParticipantRepository participantRepository;
    private final IRefereeRepository refereeRepository;
    private Referee loggedInReferee = null;


    public TriatlonService(IContestRepository contestRepository, IParticipantRepository participantRepository, IRefereeRepository refereeRepository) {
        this.contestRepository = contestRepository;
        this.participantRepository = participantRepository;
        this.refereeRepository = refereeRepository;
    }

    public boolean logInReferee(String email, String password) {
        Referee referee = this.refereeRepository.findByEmail(email);
        if (referee == null) return false;
        var isLoggedIn = referee.getEmail().equals(email) && referee.getPassword().equals(password);
        if (isLoggedIn) {
            loggedInReferee = referee;
        }
        return isLoggedIn;
    }

    public void logOutReferee() {
        loggedInReferee = null;
    }

    public Referee getCurrentReferee() {
        return loggedInReferee;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        this.participantRepository.findAll().forEach(participants::add);
        return participants;
    }

    public List<Result> getParticipantsWithTotalPoints() {
        List<Result> participantsWithResults = new ArrayList<>();
        this.participantRepository.findAll().forEach(participant -> {
            var points = 0;
                Contest contest = contestRepository.findByParticipantID(participant.getId());
                if (contest != null) points += contest.getPoints();
            Result athleteResult = new Result(participant.getId(), participant.getFullName(), participant.getFullNameReversed(), points);
            participantsWithResults.add(athleteResult);
        });
        return participantsWithResults.stream().sorted(Comparator.comparing(Result::getNameReversed)).collect(Collectors.toList());
    }

    public void addResult(Integer participantID, Integer points){
        Contest contest = this.contestRepository.findByParticipantID(participantID);
        if (contest == null) {
            contest = new Contest("", points, participantID);
            this.contestRepository.save(contest);
        } else {
            contest.setPoints(points);
            this.contestRepository.update(contest);
        }
    }

    public List<Result> getParticipantsWithResultInRace() {
        List<Result> results = new ArrayList<>();
        this.contestRepository.findAllWithPoints().forEach(participantPoints -> {
            Participant participant = this.participantRepository.findOne(participantPoints.getParticipantID());
            Result result = new Result(participant.getId(), participant.getFullName(), participant.getFullNameReversed(), participantPoints.getPoints()) {
            };
            results.add(result);
        });
        return results.stream().sorted(Comparator.comparing(Result::getPoints).reversed()).collect(Collectors.toList());
    }

}
