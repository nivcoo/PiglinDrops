package fr.nivcoo.piglindrops;

import fr.nivcoo.piglindrops.listeners.*;
import fr.nivcoo.piglindrops.menu.PiglinDropMenus;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PiglinDrops extends JavaPlugin implements Listener {

    private static PiglinDrops INSTANCE;

    private PiglinDropMenus menus;


    @Override
    public void onEnable() {
        INSTANCE = this;

        menus = new PiglinDropMenus(this);

        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);


    }


    @Override
    public void onDisable() {
        if (menus != null) menus.close();
    }

    public static PiglinDrops get() {
        return INSTANCE;
    }

    public PiglinDropMenus menus() {
        return menus;
    }

}
