package test.minecraft.mplugin.commands;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import org.bukkit.*;
import org.bukkit.command.*;

import java.util.*;

public class NotifyCmd implements CommandExecutor {
    private final Main plugin;
    private Map<Player, Integer> playerTime = new HashMap<>();

    public NotifyCmd(Main plugin) {
        this.plugin = plugin;
    }

    //Test method
    private void refreshPlayTime() {
        Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
        for (Player p : players) {
            playerTime.put(p, p.getStatistic(Statistic.PLAY_ONE_MINUTE));
        }
    }

    //Test method
    private String printPlayTime(Player p) {
        refreshPlayTime();
        //print PlayerTime of sender
        return "Your PlayerTime : " + playerTime.get(p);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 1) {
            return false;
        }

        Player self = (Player) sender;
        sender.sendMessage(ChatColor.GREEN + printPlayTime(self));
        return true;
    }
}
