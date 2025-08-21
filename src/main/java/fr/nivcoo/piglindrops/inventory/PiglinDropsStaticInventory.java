package fr.nivcoo.piglindrops.inventory;

import fr.nivcoo.piglindrops.PiglinDrops;
import fr.nivcoo.piglindrops.piglin.DropRow;
import fr.nivcoo.utilsz.inventory.ClickableItem;
import fr.nivcoo.utilsz.inventory.Inventory;
import fr.nivcoo.utilsz.inventory.InventoryProvider;
import fr.nivcoo.utilsz.inventory.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PiglinDropsStaticInventory implements InventoryProvider, Listener {

    private final ClickableItem background;
    private final ClickableItem noRead;
    private final List<DropRow> drops = new ArrayList<>();

    private final List<Integer> slots = new ArrayList<>();

    public PiglinDropsStaticInventory() {

        this.background = ClickableItem.of(
                ItemBuilder.of(Material.GRAY_STAINED_GLASS_PANE, 1, (short) 7).name(" ").build()
        );
        this.noRead = ClickableItem.of(ItemBuilder.of(Material.PAPER, 1).name("§cTu n'arrives pas à tout lire ?")
                .lore(Arrays.asList("§7 - Clique sur Echap", "§7 - Va dans \"Options\"",
                        "§7 - Puis \"Options Graphiques\"", "§7 - Puis règle la Taille de", "§7l'Interface sur 2 ou 3"))
                .build());

        for (int row = 1; row <= 4; row++) {
            for (int col = 1; col <= 7; col++) {
                slots.add(row * 9 + col);
            }
        }

        ItemStack soulSpeedBook = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta soulSpeedMeta = (EnchantmentStorageMeta) soulSpeedBook.getItemMeta();
        soulSpeedMeta.addStoredEnchant(Enchantment.SOUL_SPEED, 3, true);
        soulSpeedBook.setItemMeta(soulSpeedMeta);

        add(soulSpeedBook, "Livre Agilité des âmes", "1", "5/459 (~1,09%)", 93.8);

        ItemStack soulSpeedBoots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta soulSpeedBootsMeta = soulSpeedBoots.getItemMeta();
        if (soulSpeedBootsMeta != null) {
            soulSpeedBootsMeta.addEnchant(Enchantment.SOUL_SPEED, 3, true);
            soulSpeedBoots.setItemMeta(soulSpeedBootsMeta);
        }

        add(soulSpeedBoots, "Bottes Agilité des âmes", "1", "8/459 (~1,74%)", 58.625);

        ItemStack fireResPotionSplash = new ItemStack(Material.SPLASH_POTION);
        PotionMeta firePotionMetaSplash = (PotionMeta) fireResPotionSplash.getItemMeta();
        firePotionMetaSplash.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0), true);
        fireResPotionSplash.setItemMeta(firePotionMetaSplash);

        add(fireResPotionSplash, "Potion de résistance au feu jetable", "1", "8/459 (~1,74%)", 58.625);

        ItemStack fireResPotion = new ItemStack(Material.POTION);
        PotionMeta firePotionMeta = (PotionMeta) fireResPotion.getItemMeta();
        firePotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0), true);
        fireResPotion.setItemMeta(firePotionMeta);
        add(fireResPotion, "Potion de résistance au feu", "1", "8/459 (~1,74%)", 58.625);

        add(Material.POTION, "Fiole d'eau", "1", "10/459 (~2,18%)", 46.9);
        add(Material.DRIED_GHAST, "Ghast desséché", "1", "10/459 (~2,18%)", 46.9);
        add(Material.IRON_NUGGET, "Pépite de fer", "10–36", "10/459 (~2,18%)", 2.039);
        add(Material.ENDER_PEARL, "Perle de l’Ender", "2–4", "10/459 (~2,18%)", 15.63);
        add(Material.STRING, "Ficelle", "3–9", "20/459 (~4,36%)", 3.908);
        add(Material.QUARTZ, "Quartz du Nether", "5–12", "20/459 (~4,36%)", 2.759);
        add(Material.OBSIDIAN, "Obsidienne", "1", "40/459 (~8,71%)", 11.725);
        add(Material.FIRE_CHARGE, "Boule de feu", "1", "40/459 (~8,71%)", 11.725);
        add(Material.CRYING_OBSIDIAN, "Obsidienne pleureuse", "1–3", "40/459 (~8,71%)", 5.867);
        add(Material.LEATHER, "Cuir", "2–4", "40/459 (~8,71%)", 3.908);
        add(Material.SOUL_SAND, "Sable des âmes", "2–8", "40/459 (~8,71%)", 2.345);
        add(Material.NETHER_BRICK, "Brique du Nether", "2–8", "40/459 (~8,71%)", 2.345);
        add(Material.SPECTRAL_ARROW, "Flèche spectrale", "6–12", "40/459 (~8,71%)", 1.303);
        add(Material.GRAVEL, "Gravier", "8–16", "40/459 (~8,71%)", 0.977);
        add(Material.BLACKSTONE, "Roche noire", "8–16", "40/459 (~8,71%)", 0.977);
    }

    private void add(ItemStack icon, String name, String qty, String probText, double avgGold) {
        drops.add(new DropRow(icon, name, qty, probText, avgGold));
    }

    private void add(Material mat, String name, String qty, String probText, double avgGold) {
        drops.add(new DropRow(new ItemStack(mat), name, qty, probText, avgGold));
    }

    @Override
    public String title(Inventory inv) {
        return "§c§lPiglin Drops";
    }

    @Override
    public int rows(Inventory inv) {
        return 6;
    }

    @Override
    public void init(Inventory inv) {
        render(inv);
    }

    @Override
    public void update(Inventory inv) {
    }

    private void render(Inventory inv) {
        inv.fill(background);

        inv.set(5, 1, ClickableItem.of(ItemBuilder.of(Material.GOLD_INGOT)
                .name("§e§lObjets de troc du Piglin")
                .lore(Arrays.asList(
                        "§7Clique-droit sur un Piglin non agressif",
                        "§7pour consulter ces chances et moyennes."
                )).build()));

        inv.set(1, 6, noRead);

        inv.set(9, 6, ClickableItem.of(
                ItemBuilder.of(Material.BARRIER).name("§cFermer").build(),
                e -> inv.getPlayer().closeInventory()
        ));

        int i = 0;
        for (DropRow d : drops) {
            if (i >= slots.size()) break;
            int slot = slots.get(i++);


            List<String> lores = new ArrayList<>();
            lores.add("");
            lores.add("§8§l» §eQuantité : §6" + d.qty());
            lores.add("§8§l» §eProbabilité : §6" + d.probText());
            lores.add("§8§l» §eLingots requis : §6~" + d.avgGold());

            ItemStack displayItem = d.icon().clone();
            ItemMeta meta = displayItem.getItemMeta();

            if (meta != null) {
                meta.displayName(Component.text("§e§n" + d.name()));
                meta.lore(lores.stream().map(Component::text).collect(Collectors.toList()));
                displayItem.setItemMeta(meta);
                inv.set(slot, ClickableItem.of(displayItem));
            }
        }
    }
}
