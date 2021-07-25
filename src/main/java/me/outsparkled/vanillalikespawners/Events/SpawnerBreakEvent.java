package me.outsparkled.vanillalikespawners.Events;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.apache.commons.lang.WordUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {

        EntityEquipment playerEquip = e.getPlayer().getEquipment();
        if (playerEquip == null) return;

        if (e.getBlock().getType().equals(Material.SPAWNER) && playerEquip.getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && playerEquip.getItemInMainHand().getType().toString().contains("PICKAXE") && e.getPlayer().getGameMode() != GameMode.CREATIVE) {

            Location location = e.getBlock().getLocation();
            CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlock().getState();
            ItemStack spawner = new ItemStack(Material.SPAWNER);

            e.setExpToDrop(0);

            NBTItem nbti = new NBTItem(spawner);
            nbti.setString("VLSSpawnerType", creatureSpawner.getSpawnedType().toString());
            spawner = nbti.getItem();

            ItemMeta spawnerMeta = spawner.getItemMeta();
            if (spawnerMeta != null) spawnerMeta.setDisplayName("§r§f" + WordUtils.capitalizeFully(creatureSpawner.getSpawnedType().toString().replace("_", " ")) + " Spawner");
            spawner.setItemMeta(spawnerMeta);

            World world = location.getWorld();
            if (world != null) world.dropItemNaturally(location, spawner);
        }
    }
}
