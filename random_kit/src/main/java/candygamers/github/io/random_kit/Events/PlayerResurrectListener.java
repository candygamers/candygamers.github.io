package candygamers.github.io.random_kit.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerResurrectListener implements Listener {
    private JavaPlugin plugin;

    public PlayerResurrectListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerResurrect(EntityResurrectEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            // Delay the application of Night Vision
            Bukkit.getScheduler().runTaskLater(plugin, () -> applyNightVision(player), 20L); // 20 ticks = 1 second delay
        }
    }

    private void applyNightVision(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false, true));
    }
}
