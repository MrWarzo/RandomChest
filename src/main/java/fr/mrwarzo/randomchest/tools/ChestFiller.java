package fr.mrwarzo.randomchest.tools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ChestFiller {
    public static void fillChest(Chest chest, List<ItemStack> itemsList) {
        Random rnd = new Random();

        for (int i = 0; i < chest.getBlockInventory().getSize(); i++) {
            if (rnd.nextFloat() <= 0.5f) {
                chest.getBlockInventory().setItem(i, itemsList.get(rnd.nextInt(itemsList.size())));
            }
        }
    }

    public static List<ItemStack> itemsList() {
        List<ItemStack> list = new ArrayList<>();
        Random rnd = new Random();

        list.add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, rnd.nextInt(64)));
        list.add(new ItemStack(Material.NETHERITE_SWORD));
        list.add(new ItemStack(Material.BOOK, rnd.nextInt(64)));
        list.add(new ItemStack(Material.EGG, rnd.nextInt(16)));
        list.add(new ItemStack(Material.DANDELION, rnd.nextInt(64)));
        list.add(new ItemStack(Material.ROSE_BUSH, rnd.nextInt(64)));
        list.add(new ItemStack(Material.ALLIUM, rnd.nextInt(64)));
        list.add(new ItemStack(Material.BLUE_ORCHID, rnd.nextInt(64)));
        list.add(new ItemStack(Material.ORANGE_TULIP, rnd.nextInt(64)));
        list.add(new ItemStack(Material.PINK_TULIP, rnd.nextInt(64)));
        list.add(new ItemStack(Material.RED_TULIP, rnd.nextInt(64)));
        list.add(new ItemStack(Material.WHITE_TULIP, rnd.nextInt(64)));
        list.add(new ItemStack(Material.DEAD_BUSH, rnd.nextInt(64)));
        list.add(new ItemStack(Material.WITHER_ROSE, rnd.nextInt(64)));
        list.add(new ItemStack(Material.OXEYE_DAISY, rnd.nextInt(64)));
        list.add(new ItemStack(Material.AZURE_BLUET, rnd.nextInt(64)));
        list.add(new ItemStack(Material.SEA_PICKLE, rnd.nextInt(64)));
        list.add(new ItemStack(Material.WARPED_ROOTS, rnd.nextInt(64)));
        list.add(new ItemStack(Material.LARGE_FERN, rnd.nextInt(64)));
        list.add(new ItemStack(Material.LARGE_FERN, rnd.nextInt(64)));

        Player rndPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[rnd.nextInt(Bukkit.getOnlinePlayers().size())];
        list.add(new ItemBuilder(Material.PLAYER_HEAD).setName(rndPlayer.getName()).setSkullOwner(rndPlayer.getName()).toItemStack());

        return new ArrayList<>(list);
    }
}
