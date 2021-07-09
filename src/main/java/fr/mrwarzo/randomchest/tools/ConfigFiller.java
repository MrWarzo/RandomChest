package fr.mrwarzo.randomchest.tools;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigFiller implements CFGFiller{
    @Override
    public void fill(FileConfiguration fileConfiguration) {
        ConfigurationSection rcSection = fileConfiguration.createSection("randomchest");

        rcSection.set("nb-valide", "§4Vous devez entrer un nombre valide !");
        rcSection.set("spawn-success", "§aVotre coffre a apparu.");
        rcSection.set("spawn-success-multi", "§aVos coffres ont apparus.");
        rcSection.set("max-chest", 32);
        rcSection.set("too-much", "§4Vous avez dépasser la quantitée §lmaximale §r§4de coffre ! Vous êtes limité à : §5" + rcSection.getInt("max-chest"));

    }
}
