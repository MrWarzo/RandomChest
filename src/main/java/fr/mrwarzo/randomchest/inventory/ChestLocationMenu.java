package fr.mrwarzo.randomchest.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.mrwarzo.randomchest.managers.Managers;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static fr.mrwarzo.randomchest.tools.ChestFiller.fillChest;
import static fr.mrwarzo.randomchest.tools.ChestFiller.itemsList;

public class ChestLocationMenu implements InventoryProvider {
    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("chestLocationMenu")
            .provider(new ChestLocationMenu())
            .size(6, 9)
            .title("Où voulez-vous faire apparaître le coffre : ")
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fill(ClickableItem.of(new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                e -> putChest(player, contents, e.getSlot())
        ));
        contents.set(5, 4, ClickableItem.of(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE),
                e -> player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 100, 1)
        ));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }

    public void putChest(Player player, InventoryContents contents, int slot) {
        FileConfiguration cfg = Managers.getConfigManager().getConfigurationFile("config.yml");
        ConfigurationSection rcSection = cfg.getConfigurationSection("randomchest");
        Location location = player.getLocation().toBlockLocation();
        World world = location.getWorld();
        int x = slot % 9;
        int z = slot / 9;

        if(player.getFacing() == BlockFace.EAST || player.getFacing() == BlockFace.WEST) {
            x -= 4;
            z -= 5;

            int temp = x;
            x = z;
            z = temp;
            z *= -1;

            if(player.getFacing() == BlockFace.EAST) {
                x *= -1;
                z *= -1;
            }
        }
        else {
            x -= 4;
            z -= 5;
            if(player.getFacing() == BlockFace.SOUTH) {
                x *= -1;
                z *= -1;
            }
        }

        Block block = world.getBlockAt(location.add(x,0,z));
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();

        fillChest(chest, itemsList());

        player.sendMessage(rcSection.getString("spawn-success"));
        INVENTORY.close(player);
    }
}
