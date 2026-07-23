package fr.nivcoo.piglindrops.listeners;

import fr.nivcoo.piglindrops.PiglinDrops;
import org.bukkit.Material;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    private final PiglinDrops plugin;

    public InteractListener() {
        plugin = PiglinDrops.get();
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Piglin piglin)) return;
        if (piglin instanceof PiglinBrute) return;

        if (piglin.getTarget() != null) return;

        Player player = event.getPlayer();

        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
        if (hand.getType() == Material.GOLD_INGOT || hand.getType() == Material.GOLD_NUGGET) {
            return;
        }

        if (!player.hasPermission("piglindrops.open.menu")) {
            return;
        }

        event.setCancelled(true);

        plugin.menus().open(player);

    }
}
