package candygamers.github.io.deathprotectionplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.Map;
import java.util.UUID;

import static candygamers.github.io.deathprotectionplugin.TimeUtils.getIsraelTime;
public class KillListener implements Listener {

    // Stores the UUID of the killer for each player
    private final Map<UUID, UUID> killers;
    // Stores the death count for each player
    private final Map<UUID, Integer> deathCounts;
    // Stores the time of the first kill for each player in milliseconds
    private final Map<UUID, Long> firstKillTimes;

    public KillListener(Map<UUID, UUID> killers, Map<UUID, Integer> deathCounts, Map<UUID, Long> firstKillTimes) {
        this.killers = killers;
        this.deathCounts = deathCounts;
        this.firstKillTimes = firstKillTimes;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            Player victim = event.getEntity();

            UUID killerUuid = killer.getUniqueId();
            UUID victimUuid = victim.getUniqueId();

            if (killers.get(victimUuid) != null && killers.get(victimUuid).equals(killerUuid)) {
                int newDeathCount = deathCounts.getOrDefault(victimUuid, 0) + 1;
                deathCounts.put(victimUuid, newDeathCount);

                if (newDeathCount == 5) {
                    Long firstKillTime = System.currentTimeMillis();
                    firstKillTimes.put(victimUuid, firstKillTime);
                    String expirationTime = getIsraelTime(firstKillTime + 15 * 60 * 1000); // add 15 minutes to the first kill time
                    killer.sendMessage(ChatColor.RED + "הגעת למקסימום כמות הריגות של שחקן זה נסה שוב מאוחר יותר בעוד " + expirationTime);
                    victim.sendMessage(ChatColor.RED + "אתה לא יכול להרוג את מי שהרג אותך עד שיגמר הזמן הגנה שלך ממנו " + expirationTime);
                }
            } else {
                killers.put(victimUuid, killerUuid);
                deathCounts.put(victimUuid, 1);
                firstKillTimes.put(victimUuid, System.currentTimeMillis());
            }
        }
    }
}