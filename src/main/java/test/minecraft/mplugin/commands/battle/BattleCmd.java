package test.minecraft.mplugin.commands.battle;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;

public class BattleCmd implements CommandExecutor {
    private final Main plugin;

    private BattleMgrTst battleGame;

    public BattleCmd(Main plugin) {
        this.plugin = plugin;
        this.battleGame = new BattleMgrTst(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String CommandIdentifier;
        try {
            CommandIdentifier = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        Player p = (Player) sender;

        // Check player on Normal world
        if (p.getWorld().getEnvironment() != World.Environment.NORMAL) {
            return false;
        }

        battleGame.setWorld(p.getWorld());

        if (CommandIdentifier.equalsIgnoreCase("Init")) {
            try {
                initCmd(p, args);
            } catch (Exception e) {
                if (e.getCause() instanceof ArrayIndexOutOfBoundsException) {
                    return false;
                } else if (e.getCause() instanceof NumberFormatException) {
                    sender.sendMessage(ChatColor.RED + "Number argument must be ~ or number");
                    return false;
                } else if (e.getCause() instanceof IllegalArgumentException) {
                    sender.sendMessage(ChatColor.RED + "Too few or many argument!");
                    return false;
                } else {
                    return false;
                }
            }
        } else if (CommandIdentifier.equalsIgnoreCase("Team")) {
            //Todo: Create Team, join team
        } else if (CommandIdentifier.equalsIgnoreCase("Abli")) {
            //Todo: Check players have team
            //Todo: Give Ability each Team's player
        } else if (CommandIdentifier.equalsIgnoreCase("Start")) {
            battleGame.Start(p);

            //Todo: Get each team And tp each Team's player
        } else if (CommandIdentifier.equalsIgnoreCase("End")) {
            battleGame.End();
        } else {
            //Todo return false;
            return false;
        }

        return true;
    }

    void initCmd(@NotNull Player p, @NotNull String[] args) throws Exception {
        String detailCmd = args[1];
        if (detailCmd.equalsIgnoreCase("setlo")) {
            if (args.length != 6)
                throw new IllegalArgumentException();

            double xpos, ypos, zpos;

            xpos = args[2].equals("~") ? p.getLocation().getX() : Integer.parseInt(args[2]);
            ypos = args[3].equals("~") ? p.getLocation().getY() : Integer.parseInt(args[3]);
            zpos = args[4].equals("~") ? p.getLocation().getZ() : Integer.parseInt(args[4]);

            battleGame.setCoord(xpos, ypos, zpos);
            //Todo: Get Coordinate
        } else if (detailCmd.equalsIgnoreCase("setwb")) {
            int size = Integer.parseInt(args[2]);
            int sec = Integer.parseInt(args[3]);
            battleGame.setWbSizeSec(size, sec);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
