package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import test.minecraft.mplugin.Main;
import test.minecraft.mplugin.core.TitleMaker;

import java.util.*;

class playerTask extends BukkitRunnable {
    private final Main plugin;
    private final BattleMgrTst battleMgrTst;

    private Title[] title;
    private HashSet<ItemStack> items;

    private BossBar bossBar;

    playerTask(Main plugin, BattleMgrTst bt) {
        this.plugin = plugin;
        this.battleMgrTst = bt;
        this.title = new TitleMaker().makeCountdown(3);
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
                    Map<ItemStack, ItemMeta> iMap = new HashMap<>();

                    ItemStack knockStick = new ItemStack(Material.STICK, 1);
                    ItemStack torch = new ItemStack(Material.TORCH, 1);
                    ItemMeta ksIm = knockStick.getItemMeta();
                    ItemMeta trIm = torch.getItemMeta();

                    long delay = 0;

                    ksIm.addEnchant(Enchantment.KNOCKBACK, 1, true);
                    trIm.addEnchant(Enchantment.KNOCKBACK, 0, true);

                    iMap.put(knockStick, ksIm);
                    iMap.put(torch, trIm);

                    addItem(iMap);

//                    items.add(knockStick);

                    // Tasks for Each players
                    for (Player pl : plugin.getServer().getOnlinePlayers()) {
                        Location locate = pl.getLocation();

                        battleMgrTst.addPlayerCollection(pl);

                        // Send title to player
                        for (int i = 0; i < title.length; i++) {
                            Title t = title[i];
                            delay = (20 * i);
                            Location plLocation = pl.getLocation();

                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                pl.playSound(plLocation, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                pl.sendTitle(t);
                            }, delay);
                        }

                        // Settings for player
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                            pl.teleport(battleMgrTst.getCenter());
                            pl.getInventory().clear();
                            pl.setGameMode(GameMode.ADVENTURE);
                            for (ItemStack is : items) {
                                pl.getInventory().addItem(is);
                            }
                            fullHeal(pl);
                            pl.playSound(battleMgrTst.getCenter(), Sound.MUSIC_DISC_WARD, 1, 1);
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
