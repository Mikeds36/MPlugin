package test.minecraft.mplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCmd implements CommandExecutor {
    private final Main plugin;
    private final GameMode[] gm = GameMode.values();

    public GamemodeCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length > 2) {
            return false;
        }

        Player player = (Player) sender;

        PluginDescriptionFile pluginInfo = plugin.getDescription();
        String Usage = (String) pluginInfo.getCommands().get("gm").get("usage");

        int UIGamemode = Integer.parseInt(args[0]);
        GameMode uigm = gm[UIGamemode];

        if (args.length == 1) {
            if (sender.isOp()) {
                if (UIGamemode < 4) {
                    player.setGameMode(uigm);
                    sender.sendMessage(ChatColor.GREEN + "Your gamemode is Changed to " + uigm.name());
                } else {
                    sender.sendMessage(ChatColor.RED + Usage);
                }
            } else {
                sender.sendMessage("You don't have Permission");
            }
        } else {
            String PersonParms = args[1];
            if (PersonParms.isEmpty()) {
                return false;
            }
            if (sender.isOp()) {
                if (PersonParms.equalsIgnoreCase("@p")) {
                    if (UIGamemode < 4) {
                        player.setGameMode(uigm);
                        sender.sendMessage(ChatColor.GREEN + "Your gamemode has been Changed to " + uigm.name());
                    } else {
                        sender.sendMessage(ChatColor.RED + Usage);
                    }
                } else if (PersonParms.equalsIgnoreCase("@a")) {
                    List<Entity> ea = new ArrayList<>(Bukkit.getOnlinePlayers());
                    if (UIGamemode < 4) {
                        for (Entity e : ea) {
                            Player a = (Player) e;
                            a.setGameMode(uigm);
                            sender.sendMessage(ChatColor.GREEN + a.getName() + "'s gamemode has been Changed to " + uigm.name());
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + Usage);
                    }
                } else {
                    List<Entity> ea = new ArrayList<>(Bukkit.getOnlinePlayers());
                    if (ea.contains(Bukkit.getPlayer(PersonParms))) {
                        //PersonParms
                        Player p = Bukkit.getPlayer(PersonParms);
                        if (p != null) {
                            if (UIGamemode < 4) {
                                p.setGameMode(uigm);
                                p.sendMessage(ChatColor.GREEN + "Your gamemode is Changed to " + uigm.name());
                            } else {
                                sender.sendMessage(ChatColor.RED + Usage);
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("You don't have Permission");
            }
        }

        return true;
    }
}
