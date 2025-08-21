package fr.nivcoo.piglindrops.piglin;

import org.bukkit.inventory.ItemStack;

public record DropRow(ItemStack icon, String name, String qty, String probText, double avgGold) {
}
