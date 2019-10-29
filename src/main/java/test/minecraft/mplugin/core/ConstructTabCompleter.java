package test.minecraft.mplugin.core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ConstructTabCompleter {
    private ConstructTabCompleter() {

    }

    public static class Gamemode implements TabCompleter {
        @Override
        public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
            if (!(sender instanceof Player)) {
                return null;
            }

            switch (args.length) {
                // 첫 번째 인자 자동완성
                case 1:
                    return Arrays.asList("0", "1", "2", "3");
                // 두 번째 인자 자동완성
                case 2:
                    Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
                    List<String> list = new ArrayList<>(Collections.singletonList("@a"));

                    for (Player p : players) {
                        list.add(p.getName());
                    }

                    //대소문자 구별없이 정렬
                    list.sort(String::compareToIgnoreCase);
                    return list;
            }

            return null;
        }
    }

}
