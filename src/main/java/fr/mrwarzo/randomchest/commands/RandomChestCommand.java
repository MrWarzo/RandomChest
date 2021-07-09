package fr.mrwarzo.randomchest.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import fr.mrwarzo.randomchest.inventory.ChestLocationMenu;
import fr.mrwarzo.randomchest.managers.Managers;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("rc|randomchest|rndchest")
public class RandomChestCommand extends BaseCommand {
    @Default
    @Syntax("/randomchest [amount]")
    @CommandPermission("bukit.command.op")
    public static void onGenerate(Player player, String[] args) {
        FileConfiguration cfg = Managers.getConfigManager().getConfigurationFile("config.yml");
        ConfigurationSection rcSection = cfg.getConfigurationSection("randomchest");
        int amount = 0;
        if (args.length > 0) {
            if (args.length == 1) {
                try {
                    amount = Integer.parseInt(args[0]);
                } catch (IllegalArgumentException exception) {
                    player.sendMessage(rcSection.getString("nb-valide"));
                    return;
                }
            }
            Location location = player.getLocation();
            double X = location.getDirection().getX();
            double Z = location.getDirection().getZ();
            Block block = location.getWorld().getBlockAt(location.clone().add(new Vector(X, 0, Z)));
            int maxChest = rcSection.getInt("max-chest");

            if (amount == 1) {
                block.setType(Material.CHEST);
                player.sendMessage(rcSection.getString("spawn-success"));
            } else {
                for (Location l : getSquare(player, 2, amount, maxChest)) {
                    l.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ()).setType(Material.CHEST);
                }
                player.sendMessage(rcSection.getString("spawn-success-multi"));
            }
        } else {
            ChestLocationMenu.INVENTORY.open(player);
        }
    }

    @Subcommand("info")
    @Syntax("/randomchest")
    public static void onRandomChest(Player player, String[] args) {
        player.sendMessage("§6------------------------");
        player.sendMessage("§6Nom:      RandomChest");
        player.sendMessage("§6Auteur:   MrWarzo");
        player.sendMessage("§6Version:  1.0.0");
        player.sendMessage("§6------------------------");
    }

    public static List<Location> getSquare(Player player, int radius, int amount, int maxChest) {
        FileConfiguration cfg = Managers.getConfigManager().getConfigurationFile("config.yml");
        ConfigurationSection rcSection = cfg.getConfigurationSection("randomchest");
        Location center = player.getLocation().toBlockLocation();
        World world = center.getWorld();
        List<Location> locations = new ArrayList<>();
        Location blockLocation = new Location(center.getWorld(), center.getBlockX(), center.getBlockY(), center.getBlockZ());
        Location[] corners = {
                blockLocation.clone().add(new Vector(radius, 0, -radius)),
                blockLocation.clone().add(new Vector(-radius, 0, -radius)),
                blockLocation.clone().add(new Vector(-radius, 0, radius)),
                blockLocation.clone().add(new Vector(radius, 0, radius))
        };
        int y = 0;

        if(amount > maxChest) {
            amount = maxChest;
            player.sendMessage(rcSection.getString("too-much"));
        }
        for (int i = 1; i < amount + 1; i++) {
            Location chestLoc = corners[i % 4].add(0, y, 0);

            if (i == 4) {
                y++;
            }

            locations.add(chestLoc.clone());
        }

        return locations;
    }
}
