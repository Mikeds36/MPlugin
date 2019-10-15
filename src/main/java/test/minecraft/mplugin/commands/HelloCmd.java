package test.minecraft.mplugin.commands;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import org.bukkit.*;
import org.bukkit.command.*;

public class HelloCmd implements CommandExecutor {
    private final Main plugin;

    public HelloCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 1) {
            return false;
        }
        String motd = plugin.getServer().getMotd();

        sender.sendMessage(ChatColor.GREEN + "Hello, World!\n" + motd);
        return true;
    }
}
