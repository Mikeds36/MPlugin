package test.minecraft.mplugin.commands.battle;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;
import org.bukkit.*;
import org.bukkit.command.*;

public class BattleCmd implements CommandExecutor {
    private final Main plugin;

    private BattleMgr battleGame;

    public BattleCmd(Main plugin) {
        this.plugin = plugin;
        this.battleGame = new BattleMgr(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String CommandIdentifier;
        try {
            CommandIdentifier = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            CommandIdentifier = "t";
        }

        Player p = (Player) sender;

        // Check player on Normal world
        if (p.getWorld().getEnvironment() != World.Environment.NORMAL) {
            return false;
        }

        battleGame.setWorld(p.getWorld());

        if (CommandIdentifier.equalsIgnoreCase("Init")) {
            String detailCmd = args[1];
            if (detailCmd.equalsIgnoreCase("setlo")) {
                if (args.length != 6) {
                    return false;
                }

                try {
                    double xpos, ypos, zpos;

                    xpos = args[2].equals("~") ? p.getLocation().getX() : Integer.parseInt(args[2]);
                    ypos = args[3].equals("~") ? p.getLocation().getY() : Integer.parseInt(args[3]);
                    zpos = args[4].equals("~") ? p.getLocation().getZ() : Integer.parseInt(args[4]);

                    battleGame.setCoord(xpos, ypos, zpos);
                } catch (NumberFormatException e) {
                    sender.sendMessage(Color.RED + "Coordinate must be ~ or number");
                    return false;
                }
                //Todo: Get Coordinate
            } else if (detailCmd.equalsIgnoreCase("setwb")) {
                try {
                    int size = Integer.parseInt(args[2]);
                    int sec = Integer.parseInt(args[3]);
                    battleGame.setSizeWSec(size, sec);
                } catch (NumberFormatException e) {
                    sender.sendMessage(Color.RED + "Size and Second must be number");
                }
            } else {
                return false;
            }

            //Todo: Init Something
        } else if (CommandIdentifier.equalsIgnoreCase("Team")) {
            //Todo: Create Team, join team
        } else if (CommandIdentifier.equalsIgnoreCase("Abli")) {
            //Todo: Check players have team
            //Todo: Give Ability each Team's player
        } else if (CommandIdentifier.equalsIgnoreCase("Start")) {
            battleGame.Start(p);

            //Todo: Get each team And tp each Team's player
        } else {
            return false;
        }

        return true;
    }
}
