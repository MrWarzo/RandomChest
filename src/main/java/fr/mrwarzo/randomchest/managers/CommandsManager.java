package fr.mrwarzo.randomchest.managers;

import co.aikar.commands.PaperCommandManager;
import fr.mrwarzo.randomchest.RandomChest;
import fr.mrwarzo.randomchest.commands.RandomChestCommand;

public class CommandsManager {
    public static void loadCommands(RandomChest instance) {
        PaperCommandManager cmdManager = new PaperCommandManager(instance);

        cmdManager.registerCommand(new RandomChestCommand());
    }
}
