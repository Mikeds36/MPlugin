package test.minecraft.mplugin.core;

import com.destroystokyo.paper.Title;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;

public class sendDelayTitle {
    private Player p;
    private String main, sub;
    private int fadeIn, fadeOut, stay;
    private long d;

    public sendDelayTitle(Player p, String main, String sub, int fadeIn, int fadeOut, int stay, int delay) {
        this.p = p;
        this.main = main;
        this.sub = sub;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
        this.d = delay * 20L;
    }

    public boolean sendtoP() {
        @NotNull Main plugin = new Main();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Title.@NotNull Builder tb = new Title.Builder();
            Title title = tb.subtitle(this.sub).title(this.main).fadeIn(this.fadeIn).fadeOut(this.fadeOut).stay(this.stay).build();
            this.p.sendTitle(title);
        }, this.d); // (20 ticks = 1 second)

        return true;
    }
}
