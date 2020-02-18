package co.atlvntis.atlantida.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Item {

    void setMaterial(Material material);
    Material getMaterial();

    void setDurabiity(int durabiity);
    short getDurability();

    void addEnchantment(Enchantment enchantment, int level);
    void addEnchantments(Map<Enchantment, Integer> enchantments);
    boolean hasEnchantment(Enchantment enchantment);
    Map<Enchantment, Integer> getEnchantments();

    void setDisplayName(String displayName);
    String getDisplayName();

    void setLore(List<String> lore);
    void setLore(String... lore);
    List<String> getLore();

    void addItemFlags(ItemFlag... itemFlags);
    void removeItemFlags(ItemFlag... itemFlags);
    boolean hasItemFlag(ItemFlag itemFlag);
    Set<ItemFlag> getItemFlags();

    ItemStack get();

}
