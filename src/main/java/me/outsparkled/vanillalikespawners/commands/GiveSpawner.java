package me.outsparkled.vanillalikespawners.commands;

import me.outsparkled.vanillalikespawners.util.SpawnerItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class GiveSpawner implements TabExecutor {
    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§cPlease provide a player!");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("§cPlease a spawner type!");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("§cPlease provide a valid player!");
            return true;
        }

        EntityType type;
        try {
            type = EntityType.valueOf(args[1].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException iae) {
            sender.sendMessage("§cPlease provide a valid spawner type!");
            return true;
        }

        int amount = 1;

        if (args.length > 2) {
            try {
                amount = Integer.parseInt(args[2]);
            } catch (IllegalArgumentException ignored) {
            }
        }

        ItemStack spawner = SpawnerItemFactory.createSpawner(type, amount);

        Map<Integer, ItemStack> leftover = player.getInventory().addItem(spawner);

        if (leftover.size() > 1) {
            sender.sendMessage("§cCouldn't add all items to " + player.getName() + "'s inventory! " + leftover.size() + " items were lost!");
        } else {
            sender.sendMessage("§aSuccessfully gave " + amount + " " + type.getName() + " spawners to " + player.getName());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length < 2) {
            return null; // online players
        } else if (args.length < 3) {
            return Arrays.stream(EntityType.values()).map(EntityType::toString).collect(Collectors.toList());
        } else {
            return new ArrayList<>(); // nothing
        }
    }
}
