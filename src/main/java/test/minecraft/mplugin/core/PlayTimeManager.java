package test.minecraft.mplugin.core;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayTimeManager {
    private PlayTimeManager() {

    }

    private static class LazyHolder {
        static final PlayTimeManager INSTANCE = new PlayTimeManager();
    }

    public static PlayTimeManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private final Map<Player, Integer> playerTime = new HashMap<>();
    private final Map<Player, Integer> joinTime = new HashMap<>();

    void refresh() {
        //모든 플레이어의 플레이 시간을 가져와서 HashMap에 Player, Integer(key-value) 형태로 저장
        Set<Player> players = new HashSet<>(Bukkit.getOnlinePlayers());
        for (Player p : players) {
            playerTime.put(p, p.getStatistic(Statistic.PLAY_ONE_MINUTE));
        }
    }

    void joinPlayer(@NotNull Player p) {
        //PlayerJoinEvent
        int currentPlayTime = p.getStatistic(Statistic.PLAY_ONE_MINUTE);

        playerTime.put(p, currentPlayTime);
        joinTime.put(p, currentPlayTime);
    }

    private int[] getPlayTime(@NotNull Player p) {
        //모든 플레이어의 현재 플레이 시간 갱신
        this.refresh();

        int[] time = new int[3];
        int tick = playerTime.get(p) - joinTime.get(p);

        //시간, 분, 초 계산
        time[2] = tick / 20;
        time[1] = time[2] / 60;
        time[0] = time[1] / 60;

        time[2] %= 60;
        time[1] %= 60;
        return time;
    }

    //Test method
    public String print(@NotNull Player p) {
        int[] time = this.getPlayTime(p);
        //print PlayTime of sender
        return "현재까지 플레이한 시간 : " + time[0] + "시간 " + time[1] + "분 " + time[2] + "초";
    }
}
