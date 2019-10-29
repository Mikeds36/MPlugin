package test.minecraft.mplugin.core;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import test.minecraft.mplugin.Main;

public class GameEventListener {
    public static class PlayerJoin implements Listener {
        private final Main plugin;

        public PlayerJoin(Main plugin) {
            this.plugin = plugin;
        }

        @EventHandler
        public void onJoin(PlayerJoinEvent event) {
            Player p = event.getPlayer();
            String motd = plugin.getServer().getMotd();
            PlayTimeManager playTime = PlayTimeManager.getInstance();
            //플레이어가 서버에 접속 시 당시의 시간 기록 (=>joinPlayer)
            playTime.joinPlayer(p);
            //환영 메시지
            p.sendMessage(ChatColor.GREEN + motd + " 서버에 오신 것을 환영합니다, " + p.getName() + "님!");
        }
    }
}
