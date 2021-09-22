package Barfbot.leaderboard;

import Barfbot.exceptions.NegativePointsException;

import java.io.*;
import java.util.*;

public class Leaderboard {
    private String name;
    private Map<String, Integer> scores = new LinkedHashMap<>(); // the name and the score of the people in the leaderboard
    public static final String DATA_LOCATION = "src/main/java/Barfbot/leaderboard/leaderboardData/";
    private File dataFile;
    private String messageId; // the id of the message the leaderboard is shown on

    public Leaderboard(String name) throws IOException {
        this.name = name;
        loadLeaderboard();
    }

    public void loadLeaderboard() throws IOException {
        dataFile = new File(DATA_LOCATION + name + ".txt");

        if (!dataFile.createNewFile()) {
            Scanner dataReader = new Scanner(dataFile);

            if (dataReader.hasNextLine()) {
                messageId = dataReader.nextLine();
            }

            while (dataReader.hasNextLine()) {
                String[] score = dataReader.nextLine().split(":", 2);

                try {
                    scores.put(score[1], Integer.parseInt(score[0]));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {

                }
            }
        } else {
            saveLeaderboard();
        }
    }

    private void saveLeaderboard() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true))) {
            writer.write(messageId);
            writer.newLine();

            for (Map.Entry<String, Integer> score : scores.entrySet()) {
                writer.write(score.getValue() + ":" + score.getKey());
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sortLeaderboard() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(scores.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder())); // sort the entries from high to low

        for (Map.Entry<String, Integer> entry : entries) {
            scores.put(entry.getKey(), entry.getValue());
        }
    }

    public void increment(String name) {
        int points = scores.getOrDefault(name, 0);
        scores.put(name, points + 1);
        sortLeaderboard();
        saveLeaderboard();
    }

    public void decrement(String name) throws NegativePointsException {
        int points = scores.getOrDefault(name, 0);

        if (points > 0) {
            scores.put(name, points - 1);
        } else {
            throw new NegativePointsException();
        }

        sortLeaderboard();
        saveLeaderboard();
    }
}
