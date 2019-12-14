package test.minecraft.mplugin.commands.battle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;
import test.minecraft.mplugin.Main;

class serverTask extends BukkitRunnable {
    private final Main plugin;
    private final BattleMgrTst BMT;
    private final Long TICK = 20L;

    serverTask(Main plugin, BattleMgrTst bt) {
        this.plugin = plugin;
        this.BMT = bt;
    }

    @Override
    public void run() {
        int wbSize = BMT.getWbSize();
        int wbSecond = BMT.getWbSecond();
        BossBar bb = BMT.getBossBar();
        WorldBorder wb = BMT.getWorldBorder();
        Location center = BMT.getCenter();

        for (int i = wbSecond; i > 0; i--) {
            String bbs = ChatColor.BLUE + "남은 시간 : " + i;
            int finalI = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                bb.setTitle(bbs);
                bb.setProgress((double) finalI / wbSecond);
            }, (wbSecond - i) * TICK);
        }

        // WorldBorder Setting
        wb.setCenter(center);
        wb.setSize(wbSize);
        wb.setSize(0, wbSecond);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, BMT::End, wbSecond * TICK);
    }
}

