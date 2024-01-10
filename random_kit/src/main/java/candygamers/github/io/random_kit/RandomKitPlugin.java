package candygamers.github.io.random_kit;

import candygamers.github.io.random_kit.ENUMS.Kit;
import candygamers.github.io.random_kit.Events.PlayerDeathListener;
import candygamers.github.io.random_kit.Events.PlayerJoinListener;
import candygamers.github.io.random_kit.Events.PlayerRespawnListener;
import candygamers.github.io.random_kit.Events.PlayerResurrectListener;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
public final class RandomKitPlugin extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        // Other setup...
        getLogger().info("random_kit plugin has enabled");
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this); // Pass 'this' here
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerResurrectListener(this), this); // Pass 'this' here
        Kit.init(this);
        getServer().getPluginManager().registerEvents(this, this);


// Schedule a repeating task to give every player a 'ncoin' item every 10 minutes
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                ItemStack ncoin = createNcoinItem(); // Method to create the 'ncoin' item

                for (Player player : Bukkit.getOnlinePlayers()) {
                    // Removed the permission check; now all players will receive the ncoin
                    if (player.getInventory().firstEmpty() == -1) {
                        // Inventory is full, drop the item at the player's location
                        player.getWorld().dropItemNaturally(player.getLocation(), ncoin);
                    } else {
                        // Inventory has space, give the item directly
                        player.getInventory().addItem(ncoin);
                    }
                }
            }
        }, 0L, 12000L); // 1200 ticks = 10 minutes for testing

        // Schedule a repeating task to check for fireballs reaching height 40 or above and remove them
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Bukkit.getWorlds().forEach(world -> {
                world.getEntitiesByClass(Fireball.class).stream()
                        .filter(fireball -> fireball.getLocation().getY() >= 40)
                        .forEach(Entity::remove);
            });
        }, 0L, 10L); // Run the task every half second (10 ticks)
    }


    @Override
    public void onDisable() {
        getLogger().info("random_kit plugin has disabled");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deceased = event.getEntity();
        Player killer = deceased.getKiller();

        // Check if the death was caused by another distinct player
        if (killer != null && !killer.equals(deceased)) {
            // Reward XP to the killer
            killer.giveExpLevels(1);
        }

        // No changes to the default death behavior otherwise
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        // Check if the event is about catching a fish or an item
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH || event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
            // Check if the caught entity is a player
            if (event.getCaught() instanceof Player) {
                // Allow the fishing rod to hook players
                // Optional: Add custom logic if you want to pull the hooked player towards the fisher
            } else {
                // Cancel the event to prevent catching fish or items
                event.setCancelled(true);
            }
        }
        // Using the fishing rod for other purposes (like attacking with the line) is still allowed
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        // Check if the projectile is a fireball and launched above y=40
        if (event.getEntity() instanceof Fireball && event.getEntity().getLocation().getY() > 40) {
            event.setCancelled(true); // Cancel the launch event
            // Optionally, send a message to the player
            if (event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();
                player.sendMessage("You cannot launch fireballs above height 40!");
            }
        }
    }

    private ItemStack createNcoinItem() {
        ItemBuilder itemBuilder = OraxenItems.getItemById("ncoin");
        if (itemBuilder != null) {
            return itemBuilder.build();
        } else {
            getLogger().warning("The ncoin item was not found in Oraxen's configuration.");
            return null;
        }
    }
}