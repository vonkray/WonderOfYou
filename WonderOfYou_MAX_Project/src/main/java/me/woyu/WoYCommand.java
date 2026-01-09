
package me.woyu;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class WoYCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if (a.length == 0) {
            p.sendMessage("/woyu on | off");
            return true;
        }

        if (a[0].equalsIgnoreCase("on")) {
            WoYData.setOwner(p);
            p.sendMessage("You are Wonder of You.");
        }

        if (a[0].equalsIgnoreCase("off") && WoYData.isOwner(p)) {
            WoYData.clear();
            p.sendMessage("Wonder of You sealed.");
        }
        return true;
    }
}
