package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import test.minecraft.mplugin.core.TitleMaker;

import java.util.ArrayList;
import java.util.Collection;

class BattleMgr {
    private Main plugin;

    //Location: -99, 34, -18
    final private int defaultXpos = -99;
    final private int defaultYpos = 34;
    final private int defaultZpos = -18;
    final private int defaultSize = 60;
    final private int defaultSecond = 100;

    private Location gotoP = new Location(null, -90, 20, -45);
    private Location center = new Location(null, defaultXpos, defaultYpos, defaultZpos);
    private WorldBorder wb;
    private BossBar bb;

    private int wbSize = defaultSize;
    private int wbSecond = defaultSecond;

    BattleMgr(Main p) {
        this.plugin = p;
    }

    // Setter
    void setWorld(World world) {
        center.setWorld(world);
        this.wb = world.getWorldBorder();
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
    void Start(@NotNull Player p) {
        ItemStack is = new ItemStack(Material.STICK, 1);
        ItemMeta im = is.getItemMeta();
        Collection<PotionEffect> pes = new ArrayList<>();
        PotionEffect pe = new PotionEffect(PotionEffectType.HEAL, 2, 127, false, false, true);
        PotionEffect pe1 = new PotionEffect(PotionEffectType.SATURATION, 2, 127, false, false, true);
        bb = p.getServer().createBossBar(Color.BLUE + "Battle!", BarColor.BLUE, BarStyle.SOLID);

        // Add ItemMeta in ItemStack
        im.addEnchant(Enchantment.KNOCKBACK, 1, true);
        im.setDisplayName("이야야야야야");
        is.setItemMeta(im);

        // Added Each PotionEffect to PotionEffect Collection
        pes.add(pe);
        pes.add(pe1);

        // Make Title with TitleMaker
        Title[] title = new TitleMaker().makeCountdown(3);

        bb.setVisible(true);

        // if center's World is null get player's world
        if (center.getWorld() == null) {
            center.setWorld(p.getWorld());
        }

        // Tasks for Each players
        for (Player pl : plugin.getServer().getOnlinePlayers()) {
            Location l = pl.getLocation();
            for (int i = 0; i < title.length; i++) {
                Title t = title[i];
                long delay = (20 * i);
                Sound s = Sound.BLOCK_NOTE_BLOCK_PLING;
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> pl.playSound(l, s, 1, 1), delay);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> pl.sendTitle(t), delay);
            }

            // user setting
            pl.teleport(center);
            pl.getInventory().clear();
            pl.setGameMode(GameMode.ADVENTURE);
            pl.getInventory().setItemInMainHand(is);
            pl.addPotionEffects(pes);
            pl.playSound(l, Sound.MUSIC_DISC_WARD, 1, 1);
            bb.addPlayer(pl);
        }

        // Tasks For Bossbar
        for (int i = wbSecond; i > 0; i--) {
            String bbs = Color.BLUE + "남은 시간 : " + i;
            int finalI = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                bb.setTitle(bbs);
                bb.setProgress((double) finalI / wbSecond);
            }, i * 20);
        }

        // WorldBorder Setting
        wb.setCenter(center);
        wb.setSize(wbSize);
        wb.setSize(0, wbSecond);
    }

    void End() {
        gotoP.setWorld(center.getWorld());

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            p.teleport(gotoP);
            p.setGameMode(GameMode.CREATIVE);
        }

        bb.setVisible(false);

        wb.setSize(600000);
    }
}
