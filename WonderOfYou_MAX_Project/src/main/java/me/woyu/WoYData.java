
package me.woyu;

import org.bukkit.entity.Player;
import java.util.UUID;

public class WoYData {
    private static UUID owner;

    public static void setOwner(Player p) {
        owner = p.getUniqueId();
    }

    public static boolean isOwner(Player p) {
        return owner != null && owner.equals(p.getUniqueId());
    }

    public static Player getOwner() {
        return owner == null ? null :
                WonderOfYou.get().getServer().getPlayer(owner);
    }

    public static void clear() {
        owner = null;
    }
}
