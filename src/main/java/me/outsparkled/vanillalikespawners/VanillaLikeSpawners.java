package me.outsparkled.vanillalikespawners;

import me.outsparkled.vanillalikespawners.Events.SpawnerBreakEvent;
import me.outsparkled.vanillalikespawners.Events.SpawnerPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaLikeSpawners extends JavaPlugin {

    @Override
    public void onEnable() {
        SpawnerBreakEvent spawnerBreakEvent = new SpawnerBreakEvent();
        SpawnerPlaceEvent spawnerPlaceEvent = new SpawnerPlaceEvent();
        getServer().getPluginManager().registerEvents(spawnerBreakEvent, this);
        getServer().getPluginManager().registerEvents(spawnerPlaceEvent, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
