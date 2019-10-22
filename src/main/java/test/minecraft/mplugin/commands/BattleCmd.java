package test.minecraft.mplugin.commands;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import org.bukkit.*;
import org.bukkit.command.*;
import test.minecraft.mplugin.commands.battle.*;

public class BattleCmd implements CommandExecutor {
    private final Main plugin;

    public BattleCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String CommandIdentifier = args[0];

        Player p = (Player) sender;

        if (CommandIdentifier.equalsIgnoreCase("Init")) {
            new Init(this);
            //Todo: Init Something
        } else if (CommandIdentifier.equalsIgnoreCase("Team")) {
            //Todo: Create Team, join team
        } else if (CommandIdentifier.equalsIgnoreCase("Abli")) {
            //Todo: Check players have team
            //Todo: Give Ability each Team's player
        } else if (CommandIdentifier.equalsIgnoreCase("Start")) {
            //Todo: Get each team And tp each Team's player
        } else {
            //Todo return false;
            return false;
        }

        return true;
    }
}
