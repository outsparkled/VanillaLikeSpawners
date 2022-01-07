package me.outsparkled.vanillalikespawners.util;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerItemFactory {

    public static ItemStack createSpawner(EntityType entityType, int amount) {
        NBTItem spawnerNBITItem = new NBTItem(new ItemStack(Material.SPAWNER, amount));
        spawnerNBITItem.setString("VLSSpawnerType", entityType.toString());
        ItemStack spawner = spawnerNBITItem.getItem();

        ItemMeta spawnerMeta = spawner.getItemMeta();
        if (spawnerMeta != null) spawnerMeta.setDisplayName("§r§f" + WordUtils.capitalizeFully(entityType.toString().replace("_", " ")) + " Spawner");
        spawner.setItemMeta(spawnerMeta);

        return spawner;
    }
}
