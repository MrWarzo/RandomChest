package fr.mrwarzo.randomchest.inventory;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestLocationMenu implements InventoryProvider {
    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("chestLocationMenu")
            .provider(new ChestLocationMenu())
            .size(6, 9)
            .title(ChatColor.DARK_RED + "Choisissez un emplacement pour votre coffre.")
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        for(int i = 0; i < INVENTORY.getColumns(); i++) {
            for(int j = 0; j < INVENTORY.getRows(); j++) {
                if(i != 5 && j != 9) {
                    contents.fill(ClickableItem.of(new ItemStack(Material.GRAY_STAINED_GLASS_PANE),
                            e -> putChest(player, contents, e.getSlot())
                    ));
                }
                else {
                    contents.set(6, 5, ClickableItem.of(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE),
                            e -> player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 100, 1)
                    ));
                }
            }
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }

    public void putChest(Player player, InventoryContents contents, int slot) {
        int x = slot % 9;
        int y = (int) Math.round(slot / 9.0);

        player.sendMessage(player.getFacing().toString());
        contents.set(new SlotPos(x, y), ClickableItem.of(new ItemStack(Material.BROWN_STAINED_GLASS_PANE),
                e -> player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 100, 1)
        ));

    }
}
