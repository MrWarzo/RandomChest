package fr.mrwarzo.randomchest.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import fr.mrwarzo.randomchest.inventory.ChestLocationMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("rc|randomchest|rndchest")
public class RandomChestCommand extends BaseCommand {

    @Default
    @Syntax("/randomchest [amount]")
    @Description("Génère le nombre de coffre indiqué autour du joueur.")
    public static void onGenerate(Player player, String[] args) {
        int amount = 1;
        if(args.length > 0) {
            if (args.length == 1) {
                try {
                    amount = Integer.parseInt(args[0]);
                } catch (IllegalArgumentException exception) {
                    player.sendMessage("Vous devez entrer un nombre valide !");
                }
            }
            Location location = player.getLocation();
            double X = location.getDirection().getX();
            double Z = location.getDirection().getZ();
            Block block = location.getWorld().getBlockAt(location.add(new Vector(X, 0, Z)));

            if (amount == 1) {
                block.setType(Material.CHEST);
            }

            for (Location l : getSquare(location, amount)) {
                l.getWorld().getBlockAt(l).setType(Material.CHEST);
            }
        }
        else {
            ChestLocationMenu.INVENTORY.open(player);
        }
    }

    @Subcommand("info")
    @Syntax("/randomchest")
    @Description("Les informations de RandomChest.")
    public static void onRandomChest(Player player, String[] args) {
        player.sendMessage("§6------------------------");
        player.sendMessage("§6Nom:      RandomChest");
        player.sendMessage("§6Auteur:   MrWarzo");
        player.sendMessage("§6Version:  0.0.1");
        player.sendMessage("§6------------------------");
    }

    public static List<Location> getSquare(Location center, int radius) {
        List<Location> locations = new ArrayList<>();


        return locations;
    }
}
