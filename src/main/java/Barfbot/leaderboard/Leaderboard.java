package Barfbot.leaderboard;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Leaderboard {
    private String name;
    private Map<String, Integer> scores = new HashMap<>(); // the name and the score of the people in the leaderboard
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

    public void increment(String name) {
        int points = scores.getOrDefault(name, 0);
        scores.put(name, points + 1);
        saveLeaderboard();
    }

    public void decrement(String name) {
        int points = scores.getOrDefault(name, 0);

        if (points > 0) {
            scores.put(name, points - 1);
        }
        saveLeaderboard();
    }
}
