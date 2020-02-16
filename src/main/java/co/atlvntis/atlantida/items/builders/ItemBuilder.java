package co.atlvntis.atlantida.items.builders;

import co.atlvntis.atlantida.items.BukkitItem;
import co.atlvntis.atlantida.items.Item;
import javafx.util.Builder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.Map;

public class ItemBuilder implements Builder<Item> {

    protected final Item item;

    protected ItemBuilder(Item item) {
        this.item = item;
    }

    protected ItemBuilder() {
        this(new BukkitItem());
    }

    protected ItemBuilder(Material material) {
        this(new BukkitItem(material));
    }

    protected ItemBuilder(Material material, int amount) {
        this(new BukkitItem(material, amount));
    }

    public ItemBuilder withMaterial(Material material) {
        this.item.setMaterial(material);
        return this;
    }

    public ItemBuilder withDurability(int durability) {
        this.item.setDurabiity(durability);
        return this;
    }

    public ItemBuilder withEnchantment(Enchantment enchantment, int level) {
        this.item.addEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder withEnchantment(Map<Enchantment, Integer> enchantments) {
        this.item.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder withDisplayName(String displayName) {
        this.item.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder withLore(List<String> lore) {
        this.item.setLore(lore);
        return this;
    }

    public ItemBuilder withLore(String... lore) {
        this.item.setLore(lore);
        return this;
    }

    public ItemBuilder withItemFlags(ItemFlag... itemFlags) {
        this.item.addItemFlags(itemFlags);
        return this;
    }

    public static ItemBuilder of() {
        return new ItemBuilder();
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    @Override
    public Item build() {
        return item;
    }

}
