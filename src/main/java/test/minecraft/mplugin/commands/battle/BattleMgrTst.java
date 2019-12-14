package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import test.minecraft.mplugin.Main;
import test.minecraft.mplugin.core.PlayTimeManager;
import test.minecraft.mplugin.core.TitleMaker;

import java.util.ArrayList;

class BattleMgrTst {
    private Main plugin;

    //Location: -99, 34, -18
    final private int defaultXpos = -99;
    final private int defaultYpos = 34;
    final private int defaultZpos = -18;
    final private int defaultSize = 60;
    final private int defaultSecond = 100;

    private int gotoXpos = -90;
    private int gotoYpos = 20;
    private int gotoZpos = -45;

    private int wbSize = defaultSize;
    private int wbSecond = defaultSecond;

    private Location gotoP = new Location(null, gotoXpos, gotoYpos, gotoZpos);
    private Location center = new Location(null, defaultXpos, defaultYpos, defaultZpos);
    private WorldBorder worldBorder;
    private BossBar bossBar;

    private ArrayList<Player> playerCollection = new ArrayList<>();

    BattleMgrTst(Main p) {
        this.plugin = p;
    }

    private static class LazyHolder {
        static final BattleMgrTst INSTANCE = new BattleMgrTst(new Main());
    }

    public static BattleMgrTst getInstance() {
        return BattleMgrTst.LazyHolder.INSTANCE;
    }

    private boolean isPlayerAlone() {
        return playerCollection.size() == 1;
    }

    void setWorld(World world) {
        gotoP.setWorld(world);
        center.setWorld(world);
        this.worldBorder = world.getWorldBorder();
    }

    void setCoord(double xpos, double ypos, double zpos) {
        center.setX(xpos);
        center.setY(ypos);
        center.setZ(zpos);
    }

    void setWbSizeSec(int size, int sec) {
        this.wbSize = size;
        this.wbSecond = sec;
    }

    Location getCenter() {
        return this.center;
    }

    BossBar getBossBar() {
        return this.bossBar;
    }

    WorldBorder getWorldBorder() {
        return this.worldBorder;
    }

    int getWbSecond() {
        return this.wbSecond;
    }

    int getWbSize() {
        return this.wbSize;
    }

    void Start(Player p) {
        bossBar = p.getServer().createBossBar(Color.BLUE + "Battle!", BarColor.BLUE, BarStyle.SOLID);
        bossBar.setVisible(true);

        playerTask pt = new playerTask(plugin, this);
        pt.run();
    }

    void End() {
        gotoP.setWorld(center.getWorld());

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            p.teleport(gotoP);
            p.setGameMode(GameMode.CREATIVE);
        }

        bossBar.setVisible(false);

        worldBorder.setSize(600000);
    }

    class onDeathEvent implements Listener {
        @EventHandler
        public void onDeath(PlayerDeathEvent event) {
            Player p = event.getEntity();
            p.setGameMode(GameMode.SPECTATOR);
        }
    }
}

