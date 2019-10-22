package test.minecraft.mplugin.commands.notify;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;

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
        PlayTimeManager playTime = PlayTimeManager.getInstance();
        sender.sendMessage(ChatColor.GREEN + playTime.print(self));
        return true;
    }
}
