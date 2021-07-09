package fr.mrwarzo.randomchest.managers;

import fr.mrwarzo.randomchest.RandomChest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Managers {
    private static RandomChest instance;
    private static Managers managers;
    private static ConfigManager configManager;

    public void load(RandomChest instance) {
        Managers.instance = instance;
        Managers.managers = this;
        Managers.configManager = new ConfigManager(instance);

        try {
            CommandsManager.loadCommands(instance);
            configManager.setupFiles();

            // Envoie d'un message de validation à la console au démarrage
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[RandomChest] Activation reussie");
        } catch (Exception e) {

            // Envoie d'un message d'erreur à la console
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[RandomChest] Activation interrompue");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + e.toString());
        }
    }

    public void unload() {
        try {
            configManager.saveFiles();
            // Envoie d'un message de validation à la console à la fermeture
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[HopperCounter] Desactivation reussie");
        } catch (Exception e) {
            // Envoie d'un message d'erreur à la console
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[HopperCounter] Desactivation interrompue");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + e.toString());
        }
    }

    public static RandomChest getInstance() {
        return instance;
    }

    public static Managers getManagers() {
        return managers;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
}
