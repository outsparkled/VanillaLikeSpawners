package me.outsparkled.vanillalikespawners;

import me.outsparkled.vanillalikespawners.commands.GiveSpawner;
import me.outsparkled.vanillalikespawners.listeners.BlockBreakListener;
import me.outsparkled.vanillalikespawners.listeners.BlockPlaceListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VanillaLikeSpawners extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        GiveSpawner command = new GiveSpawner();
        Objects.requireNonNull(getCommand("givespawner")).setExecutor(command);
        Objects.requireNonNull(getCommand("givespawner")).setTabCompleter(command);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
