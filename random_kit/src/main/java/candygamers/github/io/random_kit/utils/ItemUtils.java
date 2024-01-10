package candygamers.github.io.random_kit.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class ItemUtils {
    public static ItemStack createCustomItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD); // Base material
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Custom Sword");
            meta.setLore(Arrays.asList("This is a", "Custom Lore"));
            item.setItemMeta(meta);
        }
        return item;
    }

    // New method for creating enchanted items
    public static ItemStack createEnchantedItem(Material material, Enchantment enchantment, int level, String displayName) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.addEnchant(enchantment, level, true);
            meta.setDisplayName(displayName);
            item.setItemMeta(meta);
        }
        return item;
    }
    public static Map<String, Object> serializeCustomItem(ItemStack item) {
        Map<String, Object> serializedItem = new HashMap<>();
        serializedItem.put("material", item.getType().toString());
        if (item.hasItemMeta()) {
            serializedItem.put("meta", item.getItemMeta().serialize());
        }
        return serializedItem;
    }

    public static ItemStack deserializeCustomItem(Map<String, Object> itemMap) {
        Material material = Material.getMaterial((String) itemMap.get("material"));
        ItemStack item = new ItemStack(material != null ? material : Material.STONE); // Fallback to STONE if material is null

        if (itemMap.containsKey("meta")) {
            try {
                Map<String, Object> metaMap = castMap(itemMap.get("meta"));
                if (metaMap != null) {
                    ItemMeta meta = (ItemMeta) ConfigurationSerialization.deserializeObject(metaMap, ConfigurationSerialization.getClassByAlias("ItemMeta"));
                    if (meta != null) {
                        item.setItemMeta(meta);
                    }
                }
            } catch (ClassCastException e) {
                Bukkit.getLogger().warning("Failed to deserialize item meta: " + e.getMessage());
                // Handle the exception (e.g., log it, use default meta, etc.)
            }
        }
        return item;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> castMap(Object object) {
        if (object instanceof Map<?, ?>) {
            return (Map<String, Object>) object;
        }
        return null;
    }
}