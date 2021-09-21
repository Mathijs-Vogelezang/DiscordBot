package Leaderboard;

public class Participant {
    private String name;
    private int points = 0;


    public Participant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void incrementPoints() {
        points++;
    }

    public void decrementPoints() {
        if (points > 0) {
            points--;
        }
    }
}
