package fr.mrwarzo.randomchest;

import fr.mrwarzo.randomchest.managers.Managers;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomChest extends JavaPlugin {
    Managers managers = new Managers();

    @Override
    public void onEnable() {
        managers.load(this);
    }

    @Override
    public void onDisable() {
        managers.unload();
    }
}
