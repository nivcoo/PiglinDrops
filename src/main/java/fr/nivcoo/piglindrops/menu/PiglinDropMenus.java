package fr.nivcoo.piglindrops.menu;

import fr.nivcoo.edenmenu.api.EdenMenuAPI;
import fr.nivcoo.edenmenu.api.service.SurfaceRegistrationScope;
import fr.nivcoo.piglindrops.PiglinDrops;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Player;

import java.io.IOException;

public final class PiglinDropMenus implements AutoCloseable {

    private static final Key DROPS = Key.key("piglindrops", "drops");

    private final SurfaceRegistrationScope scope;

    public PiglinDropMenus(PiglinDrops plugin) {
        try {
            scope = EdenMenuAPI.get().registrations().forPlugin(plugin);
            scope.registerConfiguredSurfaces(plugin.getDataFolder().toPath().resolve("edenmenu"), "edenmenu");
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to initialize PiglinDrops menu", exception);
        }
    }

    public void open(Player player) {
        EdenMenuAPI.get().surfaces().open(player, DROPS);
    }

    @Override
    public void close() {
        scope.close();
    }
}
