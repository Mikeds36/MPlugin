package test.minecraft.mplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import test.minecraft.mplugin.commands.GamemodeCmd;
import test.minecraft.mplugin.commands.HelloCmd;
import test.minecraft.mplugin.commands.NotifyCmd;
import test.minecraft.mplugin.core.GameEventListener;
import test.minecraft.mplugin.core.ConstructTabCompleter;
import test.minecraft.mplugin.core.TaskManager;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        TaskManager taskMgr = TaskManager.getInstance();

        //스케줄 설정
        //60초(1200틱)마다 모든 플레이어의 플레이 시간을 가져와서 저장
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, taskMgr.get("notify_refresh"), 0L, 1200L);

        //이벤트 등록
        getServer().getPluginManager().registerEvents(new GameEventListener.PlayerJoin(this), this);

        //명령어 등록
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCmd(this));
        Objects.requireNonNull(getCommand("gm")).setExecutor(new GamemodeCmd(this));
        Objects.requireNonNull(getCommand("notify")).setExecutor(new NotifyCmd(this));
        Objects.requireNonNull(getCommand("notifyall")).setExecutor(new NotifyCmd.BroadcastAll());

        //탭 컴플리터 등록
        Objects.requireNonNull(getCommand("gm")).setTabCompleter(new ConstructTabCompleter.Gamemode());

        //플러그인 활성화 후 메시지 출력
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + this.getName() + "] " + "Version " + this.getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
