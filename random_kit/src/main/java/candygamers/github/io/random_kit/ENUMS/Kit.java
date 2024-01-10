package candygamers.github.io.random_kit.ENUMS;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import candygamers.github.io.random_kit.utils.ItemUtils;
import org.bukkit.plugin.java.JavaPlugin;

public enum Kit {

    KIT1,
    KIT2,
    KIT3,
    KIT4,
    KIT5,
    KIT6,
    KIT7,
    KIT8,
    KIT9,
    KIT10,
    KIT11,
    KIT12;

    private static JavaPlugin plugin;

    public static void init(JavaPlugin plugin) {
        Kit.plugin = plugin;
    }
    public void apply(Player player) {
        PlayerInventory inventory = player.getInventory();
        inventory.clear();

        switch (this) {
            case KIT1:
                // Kit 1 setup
                setArmorAndOffhand(inventory,
                        Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
                        Material.SHIELD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        1, 1, 1, 1); // Level 1 for all pieces
                setInventoryContents(inventory, new Material[]{
                                Material.DIAMOND_SWORD, Material.BOW, Material.DIAMOND_PICKAXE, Material.OAK_PLANKS,
                                Material.WATER_BUCKET, Material.LAVA_BUCKET, Material.GOLDEN_APPLE, Material.COOKED_BEEF, Material.ARROW},
                        new int[]{1, 1, 1, 64, 1, 1, 3, 32, 32},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.ARROW_DAMAGE, Enchantment.DIG_SPEED, null, null, null, null, null, null});
                break;

            case KIT2:
                // Kit 2 setup
                setArmorAndOffhand(inventory,
                        Material.NETHERITE_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.NETHERITE_BOOTS,
                        Material.TOTEM_OF_UNDYING, Enchantment.PROTECTION_ENVIRONMENTAL,
                        1, 1, 1, 1); // Level 1 for all pieces
                setInventoryContents(inventory, new Material[]{
                                Material.DIAMOND_SWORD, Material.DIAMOND_PICKAXE, Material.TRIDENT, Material.COBBLESTONE,
                                Material.COOKED_BEEF, Material.WATER_BUCKET, Material.LAVA_BUCKET},
                        new int[]{1, 1, 1, 64, 32, 1, 1},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DIG_SPEED, Enchantment.LOYALTY, null, null, null, null, null});
                addTridentEnchantments(inventory, 2);
                break;



            case KIT3:
                // Kit 3 setup
                setArmorAndOffhand(inventory, Material.IRON_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.IRON_BOOTS, Material.TOTEM_OF_UNDYING, Enchantment.PROTECTION_ENVIRONMENTAL, 2, 1, 1, 2);
                setInventoryContents(inventory, new Material[]{
                                Material.NETHERITE_AXE, Material.NETHERITE_PICKAXE, Material.BOW, Material.GOLDEN_CARROT,
                                Material.OAK_PLANKS, Material.GOLDEN_APPLE, Material.LAVA_BUCKET, Material.WATER_BUCKET, Material.ARROW},
                        new int[]{1, 1, 1, 32, 64, 1, 1, 1, 32},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DIG_SPEED, Enchantment.ARROW_DAMAGE, null, null, null, null, null, null});

                // Add specific enchantments to the bow (index 2)
                ItemStack bow = inventory.getItem(2); // Assuming bow is at index 2
                if (bow != null && bow.getType() == Material.BOW) {
                    bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2); // Power 2
                }
                break;

            case KIT4:
                // Kit 4 setup
                setArmorAndOffhand(inventory,
                        Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.DIAMOND_BOOTS,
                        Material.CROSSBOW, Enchantment.PROTECTION_ENVIRONMENTAL,
                        2, 1, 1, 1); // Protection levels for armor
                setInventoryContents(inventory, new Material[]{
                                Material.NETHERITE_SWORD, Material.IRON_PICKAXE, Material.OAK_PLANKS, Material.LAVA_BUCKET,
                                Material.WATER_BUCKET, Material.TNT, Material.GOLDEN_CARROT, Material.ARROW},
                        new int[]{1, 1, 64, 1, 1, 2, 32, 32},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DIG_SPEED, null, null, null, null, null, null});

                // Add specific enchantments to the crossbow (offhand)
                ItemStack crossbow = inventory.getItemInOffHand();
                if (crossbow != null && crossbow.getType() == Material.CROSSBOW) {
                    crossbow.addUnsafeEnchantment(Enchantment.PIERCING, 2); // Piercing 2
                    crossbow.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 1); // Quick Charge 1
                }
                break;

            case KIT5:
                // Kit 5 setup
                setArmorAndOffhand(inventory,
                        Material.NETHERITE_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.IRON_BOOTS,
                        Material.FISHING_ROD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        1, 1, 1, 2); // Protection levels for armor
                setInventoryContents(inventory, new Material[]{
                                Material.NETHERITE_SWORD, Material.TRIDENT, Material.COBBLESTONE, Material.COOKED_BEEF,
                                Material.SUSPICIOUS_STEW, Material.GOLDEN_APPLE, Material.LAVA_BUCKET, Material.WATER_BUCKET},
                        new int[]{1, 1, 64, 32, 1, 1, 1, 1},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.LOYALTY, null, null, null, null, null, null});
                addTridentEnchantments(inventory, 1);
                addSuspiciousStewEffect(inventory);
                break;

            case KIT6:
                // Kit 6 setup
                setArmorAndOffhand(inventory,
                        Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.IRON_LEGGINGS, Material.DIAMOND_BOOTS,
                        Material.SHIELD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        1, 1, 2, 1); // Protection levels for armor pieces
                setInventoryContents(inventory, new Material[]{
                                Material.DIAMOND_AXE, Material.NETHERITE_PICKAXE, Material.CROSSBOW, Material.GOLDEN_CARROT,
                                Material.GOLDEN_APPLE, Material.LAVA_BUCKET, Material.WATER_BUCKET, Material.ARROW},
                        new int[]{1, 1, 1, 32, 1, 1, 1, 32},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DIG_SPEED, Enchantment.QUICK_CHARGE, null, null, null, null, null});

                // Add specific enchantments to the crossbow for Kit 6
                ItemStack crossbowKit6 = inventory.getItem(2); // Assuming crossbow for Kit 6 is at index 2
                if (crossbowKit6 != null && crossbowKit6.getType() == Material.CROSSBOW) {
                    crossbowKit6.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 2); // Quick Charge 2
                }
                break;

            case KIT7:
                // Kit 7 setup
                setArmorAndOffhand(inventory,
                        Material.NETHERITE_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.NETHERITE_BOOTS,
                        Material.TOTEM_OF_UNDYING, Enchantment.PROTECTION_ENVIRONMENTAL,
                        1, 2, 2, 1); // Protection levels for armor pieces
                setInventoryContents(inventory, new Material[]{
                                Material.NETHERITE_SWORD, Material.NETHERITE_AXE, Material.NETHERITE_PICKAXE, Material.BOW,
                                Material.ARROW, Material.GOLDEN_APPLE, Material.GOLDEN_CARROT, Material.COBBLESTONE,
                                Material.LAVA_BUCKET},
                        new int[]{1, 1, 1, 1, 32, 3, 32, 64, 1},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ALL, Enchantment.DIG_SPEED,
                                Enchantment.ARROW_DAMAGE, null, null, null, null, null});

                // Add specific enchantments to the bow (assuming it's in the fourth slot)
                ItemStack bowKit7 = inventory.getItem(3); // Assuming bow for Kit 7 is at index 3
                if (bowKit7 != null && bowKit7.getType() == Material.BOW) {
                    bowKit7.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2); // Power 2
                }

                // Adjusting the position of the water bucket to the bottom rightmost of the upper inventory
                inventory.setItem(35, new ItemStack(Material.WATER_BUCKET)); // Setting water bucket at slot 36 (index 35)
                break;

            case KIT8:
                setArmorAndOffhand(inventory,
                        Material.NETHERITE_HELMET, Material.IRON_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS,
                        Material.FISHING_ROD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        1, 2, 1, 1); // Protection levels for armor pieces
                setInventoryContents(inventory, new Material[]{
                                Material.DIAMOND_AXE, Material.NETHERITE_PICKAXE, Material.GOLDEN_CARROT, Material.GOLDEN_APPLE,
                                Material.LAVA_BUCKET, Material.WATER_BUCKET, Material.OAK_PLANKS, Material.FIRE_CHARGE},
                        new int[]{1, 1, 32, 1, 1, 1, 64, 3},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DIG_SPEED, null, null, null, null, null, null});

                // Adjusting the position of the fishing rod to the shield slot (index 40)
                inventory.setItem(40, new ItemStack(Material.FISHING_ROD));
                break;

            case KIT9:
                // Set armor and shield
                setArmorAndOffhand(inventory,
                        Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
                        Material.SHIELD, Enchantment.PROTECTION_EXPLOSIONS,
                        4, 4, 4, 4); // Blast Protection IV for all armor pieces

                // Add Protection IV to the Chestplate
                ItemStack iron_helmet = inventory.getHelmet();
                if (iron_helmet != null && iron_helmet.getType() == Material.IRON_HELMET) {
                    iron_helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); // Protection IV
                }

                ItemStack chestplate = inventory.getChestplate();
                if (chestplate != null && chestplate.getType() == Material.IRON_CHESTPLATE) {
                    chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); // Protection IV
                }

                ItemStack iron_leggings = inventory.getLeggings();
                if (iron_leggings != null && iron_leggings.getType() == Material.IRON_LEGGINGS) {
                    iron_leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); // Protection IV
                }

                ItemStack iron_boots = inventory.getBoots();
                if (iron_boots != null && iron_boots.getType() == Material.IRON_BOOTS) {
                    iron_boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4); // Protection IV
                }


                // Set inventory contents
                setInventoryContents(inventory, new Material[]{
                                Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.CROSSBOW, Material.GOLDEN_CARROT,
                                Material.GOLDEN_APPLE, Material.TNT, Material.FIRE_CHARGE, Material.TOTEM_OF_UNDYING,
                                Material.COBBLESTONE},
                        new int[]{1, 1, 1, 32, 1, 8, 2, 1, 64},
                        new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ALL, Enchantment.MULTISHOT,
                                null, null, null, null, null, null, null, null, null, null});

                // Add specific enchantments to the sword (index 0) and axe (index 1)
                ItemStack sword = inventory.getItem(0);
                if (sword != null && sword.getType() == Material.DIAMOND_SWORD) {
                    sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1); // Sharpness 1
                }

                ItemStack axe = inventory.getItem(1);
                if (axe != null && axe.getType() == Material.DIAMOND_AXE) {
                    axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1); // Sharpness 1
                }

                // Add specific enchantments to the crossbow (index 2)
                ItemStack crossbow2 = inventory.getItem(2);
                if (crossbow2 != null && crossbow2.getType() == Material.CROSSBOW) {
                    crossbow2.addUnsafeEnchantment(Enchantment.MULTISHOT, 1); // Multishot 1
                    crossbow2.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 1); // Quick Charge 1
                }

                // Add shield enchantments (index 40, shield slot)
                ItemStack shield = inventory.getItemInOffHand();
                if (shield != null && shield.getType() == Material.SHIELD) {
                    shield.addUnsafeEnchantment(Enchantment.DURABILITY, 1); // Example enchantment, you can change this
                }

                inventory.setItem(32, new ItemStack(Material.ARROW, 32)); // Setting arrow at slot 32
                inventory.setItem(33, new ItemStack(Material.OBSIDIAN, 10)); // Setting 10 obsidian at slot 33
                inventory.setItem(34, new ItemStack(Material.WATER_BUCKET)); // Setting water bucket at slot 34
                inventory.setItem(35, new ItemStack(Material.LAVA_BUCKET)); // Setting lava bucket at slot 35


                break;

            case KIT10:
                setArmorAndOffhand(inventory,
                        Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.LEATHER_BOOTS,
                        Material.FISHING_ROD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        1, 1, 1, 4); // Protection levels for armor

                // Set inventory contents
                setInventoryContents(inventory, new Material[]{
                        Material.DIAMOND_SWORD, Material.GOLDEN_PICKAXE, Material.COBWEB, Material.COOKED_BEEF,
                        Material.FIRE_CHARGE, Material.GOLDEN_APPLE, Material.POWDER_SNOW_BUCKET, Material.POWDER_SNOW_BUCKET,
                        Material.COBBLESTONE
                }, new int[]{1, 1, 8, 32, 1, 1, 1, 1, 64}, new Enchantment[]{Enchantment.DAMAGE_ALL, Enchantment.DIG_SPEED, null, null, null, null, null, null, null, null});

                // Add boots enchantments
                ItemStack leather_boots = inventory.getBoots(); // Correct method to get the boots
                if (leather_boots != null && leather_boots.getType() == Material.LEATHER_BOOTS) {
                    leather_boots.addUnsafeEnchantment(Enchantment.DURABILITY, 3); // Example enchantment, you can change this
                }
                break;

            case KIT11:
                setArmorAndOffhand(inventory,
                        Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
                        Material.SHIELD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        2, 2, 1, 1); // Protection levels for armor

                // Set inventory contents
                setInventoryContents(inventory, new Material[]{
                        Material.IRON_SWORD, Material.BOW, Material.GOLDEN_CARROT, Material.LAVA_BUCKET,
                        Material.LAVA_BUCKET, Material.LAVA_BUCKET, Material.COBBLESTONE, Material.FIRE_CHARGE,
                        Material.TNT
                }, new int[]{1, 1, 32, 1, 1, 1, 64, 2, 4}, new Enchantment[]{Enchantment.FIRE_ASPECT, Enchantment.ARROW_FIRE, null, null, null, null, null, null, null, null});

                inventory.setItem(35, new ItemStack(Material.ARROW, 16)); // Setting ARROWS at slot 35
                inventory.setItem(34, new ItemStack(Material.OBSIDIAN,10)); // Setting obsidian at slot 34

                // Add specific enchantments to the sword (index 0) and axe (index 1)
                ItemStack iron_sword = inventory.getItem(0);
                if (iron_sword != null && iron_sword.getType() == Material.IRON_SWORD) {
                    iron_sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2); // Sharpness 1
                    iron_sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2); // Sharpness 1
                }

                // Delay the application of Fire Resistance
                Bukkit.getScheduler().runTaskLater(Kit.plugin, () -> applyFireResistance(player), 20L); // 20 ticks = 1 second delay
                break;

            case KIT12:
                setArmorAndOffhand(inventory,
                        Material.TURTLE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.IRON_BOOTS,
                        Material.SHIELD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        3, 1, 1, 1); // Protection levels for armor

                // Set armor and shield with Thorns enchantment
                setArmorAndOffhand(inventory,
                        Material.TURTLE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.IRON_BOOTS,
                        Material.SHIELD, Enchantment.PROTECTION_ENVIRONMENTAL,
                        3, 1, 1, 2); // Protection levels for armor

                // Add Thorns enchantment to all armor pieces
                ItemStack helmet = inventory.getHelmet();
                if (helmet != null) {
                    helmet.addUnsafeEnchantment(Enchantment.THORNS, 2); // Thorns 2
                }

                ItemStack chestplate_kit12 = inventory.getChestplate();
                if (chestplate_kit12 != null) {
                    chestplate_kit12.addUnsafeEnchantment(Enchantment.THORNS, 1); // Thorns 1
                }

                ItemStack leggings = inventory.getLeggings();
                if (leggings != null) {
                    leggings.addUnsafeEnchantment(Enchantment.THORNS, 1); // Thorns 1
                }

                ItemStack boots = inventory.getBoots();
                if (boots != null) {
                    boots.addUnsafeEnchantment(Enchantment.THORNS, 1); // Thorns 1
                }

                // Set inventory contents
                setInventoryContents(inventory, new Material[]{
                        Material.NETHERITE_SWORD, Material.BOW, Material.COOKED_COD, Material.GOLDEN_APPLE,
                        Material.COBBLESTONE, Material.PUFFERFISH_BUCKET, Material.PUFFERFISH_BUCKET, Material.PUFFERFISH_BUCKET,
                        Material.PUFFERFISH_BUCKET
                }, new int[]{1, 1, 32, 2, 64, 1, 1, 1, 1}, new Enchantment[]{Enchantment.DAMAGE_ALL,null, null, null, null, null, null, null, null, null});

                ItemStack netherite_sword = inventory.getItem(0);
                if (netherite_sword != null && netherite_sword.getType() == Material.NETHERITE_SWORD) {
                    netherite_sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1); // Knockback 1
                }

                // Add specific enchantments to the bow (assuming it's in the fourth slot)
                ItemStack bowKit12 = inventory.getItem(1); // Assuming bow for Kit 7 is at index 3
                if (bowKit12 != null && bowKit12.getType() == Material.BOW) {
                    bowKit12.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2); // Punch 2
                }

                inventory.setItem(35, new ItemStack(Material.SPECTRAL_ARROW,32)); // Setting spectral arrow at slot 35
                break;
        }
    }


    private ItemStack createEnchantedArmor(Material material, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.addEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createEnchantedWeapon(Material material, Enchantment enchantment, int level) {
        return createEnchantedArmor(material, enchantment, level); // Reuse the same method for simplicity
    }

    private ItemStack createEnchantedTool(Material material, Enchantment enchantment, int level) {
        return createEnchantedArmor(material, enchantment, level); // Reuse the same method for simplicity
    }

    private boolean isArmor(ItemStack item) {
        switch (item.getType()) {
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
            case CHAINMAIL_HELMET:
            case CHAINMAIL_CHESTPLATE:
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_BOOTS:
                return true;
            default:
                return false;
        }
    }



    // Define the setArmorAndOffhand method correctly
    private void setArmorAndOffhand(PlayerInventory inventory, Material helmet, Material chestplate, Material leggings, Material boots, Material offhand, Enchantment enchantment, int helmetLevel, int chestplateLevel, int leggingsLevel, int bootsLevel) {
        inventory.setHelmet(ItemUtils.createEnchantedItem(helmet, enchantment, helmetLevel, ""));
        inventory.setChestplate(ItemUtils.createEnchantedItem(chestplate, enchantment, chestplateLevel, ""));
        inventory.setLeggings(ItemUtils.createEnchantedItem(leggings, enchantment, leggingsLevel, ""));
        inventory.setBoots(ItemUtils.createEnchantedItem(boots, enchantment, bootsLevel, ""));
        inventory.setItemInOffHand(new ItemStack(offhand));
    }


    private void setInventoryContents(PlayerInventory inventory, Material[] materials, int[] amounts, Enchantment[] enchantments) {
        for (int i = 0; i < materials.length; i++) {
            if (i >= inventory.getSize()) {
                break;
            }
            Material material = materials[i];
            int amount = amounts[i];
            ItemStack item;
            if (enchantments[i] != null) {
                item = ItemUtils.createEnchantedItem(material, enchantments[i], 1, "");
            } else {
                item = new ItemStack(material, amount);
            }
            inventory.setItem(i, item);
        }
    }

    private void addTridentEnchantments(PlayerInventory inventory, int tridentSlot) {
        ItemStack trident = inventory.getItem(tridentSlot); // Get trident from specified slot
        if (trident != null && trident.getType() == Material.TRIDENT) {
            trident.addUnsafeEnchantment(Enchantment.DURABILITY, 2); // Unbreaking 2
            trident.addUnsafeEnchantment(Enchantment.LOYALTY, 2);     // Loyalty 2
        }
    }

    private void addSuspiciousStewEffect(PlayerInventory inventory) {
        ItemStack stew = inventory.getItem(4); // Assuming Suspicious Stew is at index 4
        if (stew != null && stew.getType() == Material.SUSPICIOUS_STEW) {
            // Add a custom effect like Saturation to the stew
            // Note: This requires handling via a custom item meta or similar approach
        }
    }

    private void applyFireResistance(Player player) {
        int durationTicks = 10 * 60 * 20; // 10 minutes in ticks
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, durationTicks, 0, true, false, true));
    }
}
