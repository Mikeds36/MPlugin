package test.minecraft.mplugin.commands.notify;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class PlayTimeManager {
    private PlayTimeManager() {

    }

    private static class LazyHolder {
        static final PlayTimeManager INSTANCE = new PlayTimeManager();
    }

    static PlayTimeManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private Map<Player, Integer> playerTime = new HashMap<>();

    //Test method
    private void refresh() {
        Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
        for (Player p : players) {
            playerTime.put(p, p.getStatistic(Statistic.PLAY_ONE_MINUTE));
        }
    }

    //Test method
    String print(Player p) {
        refresh();
        //print PlayTime of sender
        return "Your PlayTime : " + playerTime.get(p);
    }
}
