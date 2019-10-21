package test.minecraft.mplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import test.minecraft.mplugin.commands.BattleCmd;
import test.minecraft.mplugin.commands.HelloCmd;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Hello, World!
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCmd(this));
        Objects.requireNonNull(getCommand("battle")).setExecutor(new BattleCmd(this));
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Hello, World!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
