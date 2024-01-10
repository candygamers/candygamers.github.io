package candygamers.github.io.random_kit.Events;
import candygamers.github.io.random_kit.ENUMS.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PlayerJoinListener implements Listener {
    private final Random random = new Random(); // Declare the Random object here

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        applyNightVision(player);

        if (!player.hasPlayedBefore()) {
            Kit selectedKit = Kit.values()[random.nextInt(Kit.values().length)];
            selectedKit.apply(player);
        }
    }

    private void applyNightVision(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false, true));
    }
}