package test.minecraft.mplugin.commands;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import org.bukkit.*;
import org.bukkit.command.*;

import java.util.*;

public class NotifyCmd implements CommandExecutor {
    private final Main plugin;

    public NotifyCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 1) {
            return false;
        }

        Player self = (Player) sender;
        Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
        Map<Player, Long> playerTime = new HashMap<>();

        for (Player p : players) {
            playerTime.put(p, p.getPlayerTimeOffset());
        }

        //print PlayerTime of sender
        sender.sendMessage(ChatColor.GREEN + "Your PlayerTimeOffset : \n" + playerTime.get(self));
        return true;
    }
}
