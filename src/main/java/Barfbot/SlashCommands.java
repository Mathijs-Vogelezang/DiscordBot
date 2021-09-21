package Barfbot;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class SlashCommands extends ListenerAdapter {
    public void addCommands(CommandListUpdateAction commandList) {
        CommandData leaderboardCommand = new CommandData("leaderboard",
                "Voeg een leaderboard toe of verwijder of bewerk hem!");

        leaderboardCommand.addOption(STRING, "toevoegen", "Voeg een leaderboard toe!", true)
                .addOption(STRING, "naam", "De naam van het nieuwe leaderboard.");
        leaderboardCommand.addOption(STRING, "verwijderen", "Verwijder een leaderboard!", true)
                .addOption(STRING, "naam", "De naam van het te verwijderen leaderboard");

        commandList.addCommands(leaderboardCommand);
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        String eventName = event.getName();
        switch (eventName) {
            case "add":
                createNewLeaderboard(eventName);
                break;
        }
    }

    private void createNewLeaderboard(String name) {

    }
}
