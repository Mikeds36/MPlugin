package test.minecraft.mplugin.core;

import com.destroystokyo.paper.Title;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class TitleMaker {
    public TitleMaker() {

    }

    public Title[] makeCountdown(int count) {
        Title[] title = new Title[count];
        TextComponent[] message = new TextComponent[count];

        for (int i = 0; i < count; i++) {
            message[i] = new TextComponent(Integer.toString(count - i));
            message[i].setColor(ChatColor.RED);
            message[i].setBold(true);
            title[i] = new Title(message[i], new TextComponent(), 0, 20, 0);
        }

        return title;
    }
}
