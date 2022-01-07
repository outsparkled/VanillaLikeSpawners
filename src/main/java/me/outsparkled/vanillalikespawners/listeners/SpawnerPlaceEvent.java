package me.outsparkled.vanillalikespawners.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnerPlaceEvent implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {

        if(e.getBlock().getType() == Material.SPAWNER) {

            NBTItem nbti = new NBTItem(e.getItemInHand());

            if (!nbti.hasKey("VLSSpawnerType")) return;

            CreatureSpawner cs = (CreatureSpawner) e.getBlock().getState();
            cs.setSpawnedType(EntityType.valueOf(nbti.getString("VLSSpawnerType")));
            cs.update();
        }
    }
}
