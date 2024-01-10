package candygamers.github.io.random_kit.Events;

import candygamers.github.io.random_kit.ENUMS.Kit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class PlayerRespawnListener implements Listener{
    private final JavaPlugin plugin;
    private final Random random = new Random();
    private final HashMap<UUID, Kit> lastKitMap = new HashMap<>();

    public PlayerRespawnListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        // Apply Night Vision one tick after respawn
        Bukkit.getScheduler().runTaskLater(plugin, () -> applyNightVision(player), 1L);

        Kit previousKit = lastKitMap.get(playerId);
        Kit selectedKit;

        do {
            selectedKit = Kit.values()[random.nextInt(Kit.values().length)];
        } while(selectedKit == previousKit);

        selectedKit.apply(player);
        this.lastKitMap.put(playerId, selectedKit);
      //  player.sendMessage(selectedKit.name());
    }

    private void applyNightVision(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false, true));
    }
}