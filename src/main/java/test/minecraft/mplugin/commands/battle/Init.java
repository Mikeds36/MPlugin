package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import test.minecraft.mplugin.commands.BattleCmd;

public class Init {
    private Main plugin;


    public Init(CommandSender Sender) {
        Player p = (Player) Sender;
        World w = p.getWorld();
        WorldBorder wb = w.getWorldBorder();

        //Location: -99, 34, -18
        Location center = new Location(w, -99, 34, -18);
        p.teleport(center);
        //WorldBorder Setting
        wb.setCenter(center);
        wb.setSize(60);
        wb.setSize(0, 100);
        sendDelayTitleCmd(p, "1", "", 100, 100, 20, 1);
        sendDelayTitleCmd(p, "2", "", 100, 100, 20, 2);
        sendDelayTitleCmd(p, "3", "", 100, 100, 20, 3);
    }

    private boolean sendDelayTitleCmd(Player p, String main, String sub, int fadeIn, int fadeOut, int stay, int delay) {
        long d = delay * 20;

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Title.@NotNull Builder tb = new Title.Builder();
            Title title = tb.subtitle(sub).title(main).fadeIn(fadeIn).fadeOut(fadeOut).stay(stay).build();
            p.sendTitle(title);
        }, d); // (20 ticks = 1 second)

        return true;
    }
}
