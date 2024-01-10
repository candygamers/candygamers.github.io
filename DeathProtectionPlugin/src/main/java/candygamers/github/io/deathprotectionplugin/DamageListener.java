package candygamers.github.io.deathprotectionplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DamageListener implements Listener {

    private final Map<UUID, UUID> killers;
    private final Map<UUID, Integer> deathCounts;
    private final Map<UUID, Long> firstKillTimes;
    private Map<UUID, Map<UUID, Integer>> attackCounts; // Tracks attacks (both bow and sword)

    public DamageListener(Map<UUID, UUID> killers, Map<UUID, Integer> deathCounts, Map<UUID, Long> firstKillTimes) {
        this.killers = killers;
        this.deathCounts = deathCounts;
        this.firstKillTimes = firstKillTimes;
        this.attackCounts = new HashMap<>(); // Initialize the map
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Player || event.getDamager() instanceof Arrow) && event.getEntity() instanceof Player) {
            handleAttack(event);
        }

        // Additional logic for protection checks and handling
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Player defender = (Player) event.getEntity();

            if (isProtected(defender.getUniqueId(), attacker.getUniqueId())) {
                event.setCancelled(true);
                Long firstKillTime = firstKillTimes.get(defender.getUniqueId());
                String expirationTime = TimeUtils.getIsraelTime(firstKillTime + 15 * 60 * 1000); // add 15 minutes to the first kill time
                attacker.sendMessage(ChatColor.RED + "הגעת למקסימום כמות הריגות של שחקן זה נסה שוב מאוחר יותר בעוד " + expirationTime);
            }

            if (isProtected(attacker.getUniqueId(), defender.getUniqueId())) {
                event.setCancelled(true);
                Long firstKillTime = firstKillTimes.get(attacker.getUniqueId());
                String expirationTime = TimeUtils.getIsraelTime(firstKillTime + 15 * 60 * 1000); // add 15 minutes to the first kill time
                attacker.sendMessage(ChatColor.RED + "אתה לא יכול להרוג את מי שהרג אותך עד שיגמר הזמן הגנה שלך ממנו " + expirationTime);
            }
        }
    }

    private boolean isProtected(UUID defenderUuid, UUID attackerUuid) {
        UUID killerUuid = killers.get(defenderUuid);
        if (killerUuid != null && killerUuid.equals(attackerUuid)) {
            Integer deathCount = deathCounts.get(defenderUuid);
            Long firstKillTime = firstKillTimes.get(defenderUuid);
            return deathCount != null && deathCount >= 5 && firstKillTime != null && (System.currentTimeMillis() - firstKillTime <= 15 * 60 * 1000);
        }
        return false;
    }

    private void handleAttack(EntityDamageByEntityEvent event) {
        Player attacker;
        if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                attacker = (Player) arrow.getShooter();
            } else {
                return; // The arrow was not shot by a player
            }
        } else if (event.getDamager() instanceof Player) {
            attacker = (Player) event.getDamager();
        } else {
            return; // The damager is not a player or an arrow
        }

        Player defender = (Player) event.getEntity();

        // Check if the defender is protected against this attacker
        if (isProtected(defender.getUniqueId(), attacker.getUniqueId())) {
            event.setCancelled(true);
            // You can add a message to the attacker if needed
        }

        // Rest of your attack handling logic...
        // Note: Since you mentioned that you want the protection to trigger on kills, not hits,
        // you may not need to update the attackCounts map here.
    }
}