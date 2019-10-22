package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.commands.BattleCmd;

public class Init {

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
        Title.@NotNull Builder tb = new Title.Builder();
        Title title = tb.subtitle("this is subtitle").title("Hello World").fadeIn(100).fadeOut(100).stay(2000).build();
        p.sendTitle(title);
    }
}
