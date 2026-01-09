
package me.woyu;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import java.util.*;

public class WoYListener implements Listener {

    private final Map<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (WoYData.isOwner(p)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player owner = WoYData.getOwner();
        if (owner == null) return;

        Player t = e.getPlayer();
        if (t.equals(owner)) return;

        double dist = t.getLocation().distance(owner.getLocation());
        if (dist > 12) return;

        long now = System.currentTimeMillis();
        if (cooldown.containsKey(t.getUniqueId())
            && now - cooldown.get(t.getUniqueId()) < 500) return;

        cooldown.put(t.getUniqueId(), now);

        Vector v = t.getLocation().toVector()
                .subtract(owner.getLocation().toVector())
                .normalize().multiply(2);

        t.setVelocity(v);
        t.getWorld().strikeLightningEffect(t.getLocation());
        t.damage(4, owner);
    }
}
