package fr.nivcoo.piglindrops;

import fr.nivcoo.piglindrops.listeners.*;
import fr.nivcoo.utilsz.commands.CommandManager;
import fr.nivcoo.utilsz.config.Config;
import fr.nivcoo.utilsz.database.DatabaseManager;
import fr.nivcoo.utilsz.inventory.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class PiglinDrops extends JavaPlugin implements Listener {

    private static PiglinDrops INSTANCE;

    private InventoryManager inventoryManager;


    @Override
    public void onEnable() {
        INSTANCE = this;

        inventoryManager = new InventoryManager(this);
        inventoryManager.init();

        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);


    }


    @Override
    public void onDisable() {


    }

    public static PiglinDrops get() {
        return INSTANCE;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

}
