package candygamers.github.io.deathprotectionplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class DeathProtectionPlugin extends JavaPlugin {
    private File configFile;
    private FileConfiguration config;
    private Map<UUID, UUID> killers;
    private Map<UUID, Integer> deathCounts;
    private Map<UUID, Long> firstKillTimes;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Jerusalem"));
    @Override
    public void onEnable() {
        killers = new HashMap<>();
        deathCounts = new HashMap<>();
        firstKillTimes = new HashMap<>();

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        createConfig();
        config = YamlConfiguration.loadConfiguration(configFile);

        loadData();

        // Register events
        getServer().getPluginManager().registerEvents(new KillListener(killers, deathCounts, firstKillTimes), this);
        getServer().getPluginManager().registerEvents(new DamageListener(killers, deathCounts, firstKillTimes), this);

        // Save any existing data to the file
        saveData();
    }


    @Override
    public void onDisable() {
        saveData();
    }

    private void createConfig() {
        configFile = new File(getDataFolder(), "data.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }


    private void loadData() {
        if (config.isList("data")) {
            List<String> dataList = config.getStringList("data");

            for (String data : dataList) {
                String[] parts = data.split(",");

                UUID playerUuid = UUID.fromString(parts[0]);
                UUID killerUuid = UUID.fromString(parts[1]);
                int deathCount = Integer.parseInt(parts[2]);
                ZonedDateTime firstKillTime = ZonedDateTime.parse(parts[3], formatter);

                killers.put(playerUuid, killerUuid);
                deathCounts.put(playerUuid, deathCount);
                firstKillTimes.put(playerUuid, firstKillTime.toInstant().toEpochMilli());
            }
        }
    }

    private void saveData() {
        List<String> dataList = new ArrayList<>();

        for (UUID playerUuid : killers.keySet()) {
            UUID killerUuid = killers.get(playerUuid);
            int deathCount = deathCounts.getOrDefault(playerUuid, 0);
            long firstKillTime = firstKillTimes.getOrDefault(playerUuid, 0L);

            String firstKillTimeStr = formatter.format(Instant.ofEpochMilli(firstKillTime));
            dataList.add(playerUuid.toString() + "," + killerUuid.toString() + "," + deathCount + "," + firstKillTimeStr);
        }

        config.set("data", dataList);

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cleardeathdata")) {
            if (sender.hasPermission("deathprotectionplugin.clear")) {
                clearData();
                saveData(); // Save the cleared data to the config file
                sender.sendMessage(ChatColor.GREEN + "Death protection data cleared.");
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
            }
            return true;
        }
        return false;
    }

    private void clearData() {
        killers.clear();
        deathCounts.clear();
        firstKillTimes.clear();
        config.set("data", null); // Clear the data in the config
    }
}