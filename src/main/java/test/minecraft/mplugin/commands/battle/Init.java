package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import test.minecraft.mplugin.commands.BattleCmd;
import test.minecraft.mplugin.core.sendDelayTitle;

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
        /*
        TextComponent component = new TextComponent();
        component.setText("aaa");
        component.setColor(ChatColor.AQUA);
        TextComponent component2 = new TextComponent();
        component2.setText("aaa");
        component2.setColor(ChatColor.AQUA);
        */
        sendDelayTitle s = new sendDelayTitle(p, "1", "", 100, 100, 20, 1);
        s.sendtoP();
    }
}
