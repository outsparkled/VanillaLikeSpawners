package me.outsparkled.vanillalikespawners.listeners;

import me.outsparkled.vanillalikespawners.util.SpawnerItemFactory;
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

public class SpawnerBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {

        EntityEquipment playerEquip = e.getPlayer().getEquipment();
        if (playerEquip == null) return;

        if (e.getBlock().getType().equals(Material.SPAWNER) && playerEquip.getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && playerEquip.getItemInMainHand().getType().toString().contains("PICKAXE") && e.getPlayer().getGameMode() != GameMode.CREATIVE) {

            Location location = e.getBlock().getLocation();
            World world = location.getWorld();
            CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlock().getState();

            e.setExpToDrop(0);

            ItemStack spawner = SpawnerItemFactory.createSpawner(creatureSpawner.getSpawnedType(), 1);

            if (world != null) world.dropItemNaturally(location, spawner);
        }
    }
}
