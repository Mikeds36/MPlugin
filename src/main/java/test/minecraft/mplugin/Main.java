package test.minecraft.mplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import test.minecraft.mplugin.commands.GamemodeCmd;
import test.minecraft.mplugin.commands.HelloCmd;
import test.minecraft.mplugin.commands.notify.NotifyCmd;
import test.minecraft.mplugin.core.GameEventListener;
import test.minecraft.mplugin.core.ConstructTabCompleter;
import test.minecraft.mplugin.core.TaskManager;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Hello, World!
        TaskManager taskMgr = TaskManager.getInstance();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, taskMgr.get("notify_refresh"), 0L, 1200L);

        getServer().getPluginManager().registerEvents(new GameEventListener.PlayerJoin(this), this);
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCmd(this));
        Objects.requireNonNull(getCommand("gm")).setExecutor(new GamemodeCmd(this));
        Objects.requireNonNull(getCommand("gm")).setTabCompleter(new ConstructTabCompleter.Gamemode());
        Objects.requireNonNull(getCommand("notify")).setExecutor(new NotifyCmd(this));
        Objects.requireNonNull(getCommand("notifyall")).setExecutor(new NotifyCmd.BroadcastAll());

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + this.getName() + "] " + "Version " + this.getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
