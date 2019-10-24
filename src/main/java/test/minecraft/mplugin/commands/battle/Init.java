package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import test.minecraft.mplugin.Main;
import test.minecraft.mplugin.core.TitleMaker;

import java.util.ArrayList;
import java.util.Collection;

class Init {
    private Main plugin;

    //Location: -99, 34, -18
    final private int defaultXpos = -99;
    final private int defaultYpos = 34;
    final private int defaultZpos = -18;
    final private int defaultSize = 60;
    final private int defaultSecond = 60;

    private Location center = new Location(null, defaultXpos, defaultYpos, defaultZpos);
    private World w;
    private WorldBorder wb;
    //this.wb = w.getWorldBorder();

    private int wbSize = defaultSize;
    private int wbSecond = defaultSecond;

    Init() {
    }

    // Setter
    void setWorld(World world) {
        center.setWorld(world);
    }

    void setCoord(double xpos, double ypos, double zpos) {
        center.setX(xpos);
        center.setY(ypos);
        center.setZ(zpos);
    }

    void setSizeWSec(int size, int sec) {
        this.wbSize = size;
        this.wbSecond = sec;
    }

    // Method

    void Start() {
        Title[] title = new TitleMaker().makeCountdown(3);

        for (Player pl : plugin.getServer().getOnlinePlayers()) {
            for (int i = 0; i < title.length; i++) {
                Title t = title[i];
                long delay = (20 * i);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> pl.sendTitle(t), delay);
            }

            pl.teleport(center);
        }

        // WorldBorder Setting
        wb.setCenter(center);
        wb.setSize(wbSize);
        wb.setSize(0, wbSecond);
    }
}
