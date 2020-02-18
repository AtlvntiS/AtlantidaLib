package co.atlvntis.atlantida.item.impl;

import co.atlvntis.atlantida.item.Item;
import co.atlvntis.atlantida.utils.Colors;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BukkitItem implements Item {

    protected final ItemStack stack;
    protected final ItemMeta meta;

    protected BukkitItem(ItemStack stack, ItemMeta meta) {
        this.stack = stack;
        this.meta = meta;
    }

    public BukkitItem(Item item) {
        this.stack = item.get();
        this.meta = stack.getItemMeta();
    }

    public BukkitItem() {
        this(Material.BARRIER);
    }

    public BukkitItem(Material material) {
        this(material, 1);
    }

    public BukkitItem(Material material, int amount) {
        this.stack = new ItemStack(material, amount);
        this.meta = stack.getItemMeta();
    }

    @Override
    public void setMaterial(Material material) {
        this.stack.setType(material);
    }

    @Override
    public Material getMaterial() {
        return this.stack.getType();
    }

    @Override
    public void setDurabiity(int durabiity) {
        this.stack.setDurability((short) durabiity);
    }

    @Override
    public short getDurability() {
        return this.stack.getDurability();
    }

    @Override
    public void addEnchantment(Enchantment enchantment, int level) {
        this.stack.addUnsafeEnchantment(enchantment, level);
    }

    @Override
    public void addEnchantments(Map<Enchantment, Integer> enchantments) {
        this.stack.addUnsafeEnchantments(enchantments);
    }

    @Override
    public boolean hasEnchantment(Enchantment enchantment) {
        return this.stack.containsEnchantment(enchantment);
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments() {
        return this.stack.getEnchantments();
    }

    @Override
    public void setDisplayName(String displayName) {
        this.meta.setDisplayName(Colors.format(displayName));
    }

    @Override
    public String getDisplayName() {
        return this.meta.getDisplayName();
    }

    @Override
    public void setLore(List<String> lore) {
        this.meta.setLore(lore.stream().map(Colors::format).collect(Collectors.toList()));
    }

    @Override
    public void setLore(String... lore) {
        this.setLore(Arrays.asList(lore));
    }

    @Override
    public List<String> getLore() {
        return this.meta.getLore();
    }

    @Override
    public void addItemFlags(ItemFlag... itemFlags) {
        this.meta.addItemFlags(itemFlags);
    }

    @Override
    public void removeItemFlags(ItemFlag... itemFlags) {
        this.meta.removeItemFlags(itemFlags);
    }

    @Override
    public boolean hasItemFlag(ItemFlag itemFlag) {
        return this.meta.hasItemFlag(itemFlag);
    }

    @Override
    public Set<ItemFlag> getItemFlags() {
        return this.meta.getItemFlags();
    }

    @Override
    public ItemStack get() {
        this.stack.setItemMeta(this.meta);
        return this.stack;
    }

    public Item clone() throws CloneNotSupportedException {
        return new BukkitItem(stack, meta);
    }



}
