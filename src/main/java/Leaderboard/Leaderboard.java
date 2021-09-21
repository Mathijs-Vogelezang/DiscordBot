package Leaderboard;

import java.util.List;
import java.util.Map;

public class Leaderboard {
    private String name;
    public Map<String, Participant> scores; // the name and the score of the people in the leaderboard
    private List<Participant> participants;

    public Leaderboard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Participant addParticipant(String name) {
        Participant participant = new Participant(name);
        participants.add(participant);
        scores.put(name, participant);

        return participant;
    }

    public void increment(String name) {
        Participant participant = scores.get(name);

        if (participant == null) {
            participant = addParticipant(name);
        }

        participant.incrementPoints();
    }
}
