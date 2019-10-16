package test.minecraft.mplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;

public class GamemodeCmd implements CommandExecutor {
    private final Main plugin;

    public GamemodeCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 1) {
            return false;
        }

        if (sender.isOp()) {
            Player player = (Player) sender;
            switch (args[1]) {
                case "0":
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(String.format("Your gamemode is Changed to Survival", ChatColor.GREEN));
                    break;
                case "1":
                    player.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(String.format("Your gamemode is Changed to Creative", ChatColor.GREEN));
                    break;
                case "2":
                    player.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(String.format("Your gamemode is Changed to Spectator", ChatColor.GREEN));
                    break;
                case "3":
                    player.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(String.format("Your gamemode is Changed to Adventure", ChatColor.GREEN));
                    break;
                default:
                    sender.sendMessage(String.format("Usage: /gm (0, 1, 2, 3)\n 0 : Survival\n 1 : Creative \n 2 : Spectator \n 3 : Adventure", ChatColor.RED));
            }
        } else {
            sender.sendMessage("You don't have Permission");
        }
        return true;
    }
}
