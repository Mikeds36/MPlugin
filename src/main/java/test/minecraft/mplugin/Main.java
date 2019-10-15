package test.minecraft.mplugin;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Hello, World!
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Hello, World!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
