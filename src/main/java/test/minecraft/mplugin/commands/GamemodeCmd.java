package test.minecraft.mplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;

import java.util.Objects;

public class GamemodeCmd implements CommandExecutor {
    private final Main plugin;
    private final GameMode[] gm = GameMode.values();

    public GamemodeCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 1) {
            return false;
        }

        PluginDescriptionFile pluginInfo = plugin.getDescription();

        int UIGamemode = Integer.parseInt(args[0]);
        GameMode uigm = gm[UIGamemode];

        String Usage = (String) pluginInfo.getCommands().get("gm").get("usage");

        if (sender.isOp()) {
            Player player = (Player) sender;
            if (UIGamemode < 4) {
                player.setGameMode(uigm);
                sender.sendMessage(ChatColor.GREEN + "Your gamemode is Changed to " + uigm.name());
            } else {
                sender.sendMessage(ChatColor.RED + Usage);
            }
        } else {
            sender.sendMessage("You don't have Permission");
        }
        return true;
    }
}
