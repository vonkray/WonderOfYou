
package me.woyu;

import org.bukkit.plugin.java.JavaPlugin;

public class WonderOfYou extends JavaPlugin {

    private static WonderOfYou instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("woyu").setExecutor(new WoYCommand());
        getServer().getPluginManager().registerEvents(new WoYListener(), this);
    }

    public static WonderOfYou get() {
        return instance;
    }
}
