package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import test.minecraft.mplugin.Main;
import test.minecraft.mplugin.core.TitleMaker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

class playerTask extends BukkitRunnable {
    private final Main plugin;
    private final BattleMgrTst battleMgrTst;

    private Title[] title;
    private ItemStack knockStick;
    private ItemMeta ksIm;
    private String ksDisname;
    private HashSet<ItemStack> items;

    private BossBar bossBar;

    playerTask(Main plugin, BattleMgrTst bt) {
        this.plugin = plugin;
        this.battleMgrTst = bt;
        this.title = new TitleMaker().makeCountdown(3);
        this.knockStick = new ItemStack(Material.STICK, 1);
        this.ksIm = knockStick.getItemMeta();
        this.ksDisname = "";
        this.items = new HashSet<>();
        this.bossBar = battleMgrTst.getBossBar();
    }

    public boolean addItem(Map<ItemStack, ItemMeta> itemMap) {
        if (itemMap.size() == 0) {
            return false;
        } else {
            for (ItemStack is : itemMap.keySet()) {
                is.setItemMeta(itemMap.get(is));
                this.items.add(is);
            }
        }
        return true;
    }

    @Override
    public void run() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plugin, // Tasks for Each players
                // user setting
                () -> {
                    long delay = 0;

                    ksIm.setDisplayName(ksDisname);
                    ksIm.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    knockStick.setItemMeta(ksIm);

                    items.add(knockStick);

                    // Tasks for Each players
                    for (Player pl : plugin.getServer().getOnlinePlayers()) {
                        Location locate = pl.getLocation();
                        for (int i = 0; i < title.length; i++) {
                            Title t = title[i];
                            delay = (20 * i);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                pl.playSound(locate, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                pl.sendTitle(t);
                            }, delay);
                        }

                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                            // user setting
                            pl.teleport(battleMgrTst.getCenter());
                            pl.getInventory().clear();
                            pl.setGameMode(GameMode.ADVENTURE);
                            for (ItemStack is : items) {
                                pl.getInventory().addItem(is);
                            }
                            fullHeal(pl);
                            pl.playSound(locate, Sound.MUSIC_DISC_WARD, 1, 1);
                            bossBar.addPlayer(pl);
                        }, delay += 20);
                    }

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> new serverTask(plugin, battleMgrTst).run(), delay);
                });
    }

    private void fullHeal(Player p) {
        Collection<PotionEffect> pAry = new ArrayList<>();
        PotionEffect pe = new PotionEffect(PotionEffectType.HEAL, 2, 127, false, false, true);
        PotionEffect pe1 = new PotionEffect(PotionEffectType.SATURATION, 2, 127, false, false, true);

        pAry.add(pe);
        pAry.add(pe1);

        p.addPotionEffects(pAry);
    }
}
