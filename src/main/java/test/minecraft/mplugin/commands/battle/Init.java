package test.minecraft.mplugin.commands.battle;

import com.destroystokyo.paper.Title;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.commands.BattleCmd;

public class Init {
    private final BattleCmd s;

    public Init(BattleCmd Sender) {
        this.s = Sender;

        Player p = (Player) s;
        World w = p.getWorld();
        WorldBorder wb = w.getWorldBorder();

        //Location: -99, 34, -18
        Location center = new Location(w, -99, 34, -18);
        p.teleport(center);
        //WorldBorder Setting
        wb.setCenter(center);
        wb.setSize(60);
        wb.setSize(0, 100);
        // p.sendTitle(new Title());
    }
}
