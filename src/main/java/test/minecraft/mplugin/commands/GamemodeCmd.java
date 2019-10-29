package test.minecraft.mplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import test.minecraft.mplugin.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GamemodeCmd implements CommandExecutor {
    private final Main plugin;

    public GamemodeCmd(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*
         * modeNum : 게임 모드 정수 값
         * gameMode : modeNum에 대응하는 GameMode
         * EntityIdentifier : 엔티티 식별자
         * player : 메시지를 보낸 플레이어
         */

        int modeNum;
        GameMode gameMode = null;
        String EntityIdentifier;
        Player player;

        //OP 권한 체크
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "Error: You don't have permission to use this command.");
            return true;
        }

        switch (args.length) {
            //인자가 2개인 경우
            case 2:
                EntityIdentifier = args[1];
                break;
            //인자가 1개인 경우
            case 1:
                EntityIdentifier = "";
                break;
            //인자가 없을 경우 return false (Usage 출력)
            case 0:
                return false;
            //3개 이상의 인자가 들어온 경우
            default:
                sender.sendMessage(ChatColor.RED + "Error: 인자의 최대 허용 갯수는 2개지만, " + args.length + "개의 인자가 입력되었습니다.");
                return true;
        }

        //플레이어 체크
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        //TODO: 플레이어가 아닌 경우 기능 구현
        else {
            sender.sendMessage(ChatColor.RED + "Error: 이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }

        //첫 번째 인자 값을 정수 값으로 변환
        try {
            modeNum = Integer.parseInt(args[0]);
        }
        //예외 : 첫 번째 인자가 숫자가 아닐 경우
        catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Error: 첫 번째 인자는 숫자가 필요합니다");
            return false;
        }

        //GameMode 체크
        try {
            switch (modeNum) {
                case 0:
                    gameMode = GameMode.SURVIVAL;
                    break;
                case 1:
                    gameMode = GameMode.CREATIVE;
                    break;
                case 2:
                    gameMode = GameMode.ADVENTURE;
                    break;
                case 3:
                    gameMode = GameMode.SPECTATOR;
            }

            Objects.requireNonNull(gameMode);
        }
        //예외 : 일치하는 gameMode를 찾을 수 없을 때
        catch (NullPointerException e) {
            sender.sendMessage(ChatColor.RED + "Error: 첫번째 인자는 0 ~ 3 사이의 값을 입력해야 합니다");
            return true;
        }

        //인자가 2개 이상이면
        if (!EntityIdentifier.isEmpty()) {
            //전체 플레이어 정보 받아오기
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());

            //TODO: @p를 사용한 경우
            //TODO: @r을 사용한 경우
            //TODO: @s를 사용한 경우
            //TODO: @e를 사용한 경우
            //@a를 사용한 경우
            if (EntityIdentifier.equalsIgnoreCase("@a")) {
                for (Player p : players) {
                    p.setGameMode(gameMode);
                    p.sendMessage(ChatColor.GREEN + "Your game mode has been updated to " + gameMode.name() + " by " + player.getName());
                }
            }
            //플레이어 이름을 입력한 경우
            else {
                //두 번째 인자 값을 플레이어 객체로 받아오기
                try {
                    Player p = Bukkit.getPlayer(EntityIdentifier);
                    Objects.requireNonNull(p).setGameMode(gameMode);
                    p.sendMessage(ChatColor.GREEN + "Your game mode has been updated to " + gameMode.name() + " by " + player.getName());
                } catch (NullPointerException e) {
                    sender.sendMessage(ChatColor.RED + "Error: 플레이어를 찾을 수 없습니다");
                }

            }
        }
        //인자가 1개일 경우
        else {
            player.setGameMode(gameMode);
            sender.sendMessage(ChatColor.GREEN + "Your game mode has been updated to " + gameMode.name());
        }
        return true;
    }
}
