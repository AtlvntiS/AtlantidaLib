package co.atlvntis.atlantida.item.builder;

import co.atlvntis.atlantida.item.GuiItem;
import co.atlvntis.atlantida.item.Item;
import co.atlvntis.atlantida.item.impl.BukkitGuiItem;
import co.atlvntis.atlantida.item.impl.BukkitSkullItem;
import org.bukkit.Material;

public class GuiItemBuilder extends ItemBuilder {

    public GuiItemBuilder(Item item) {
        super(new BukkitGuiItem(item));
    }

    public GuiItemBuilder() {
        this(new BukkitSkullItem());
    }

    public GuiItemBuilder(Material material) {
        this(new BukkitGuiItem(material));
    }

    public GuiItemBuilder(Material material, int amount) {
        this(new BukkitGuiItem(material, amount));
    }

    public GuiItemBuilder setSlot(int slot) {
        ((GuiItem)item).setSlot(slot);
        return this;
    }

    @Override
    public Item build() {
        return (GuiItem) item;
    }

    public static GuiItemBuilder of(Item item) {
        return new GuiItemBuilder(item);
    }

    public static GuiItemBuilder of(Material material) {
        return new GuiItemBuilder(material);
    }

    public static GuiItemBuilder of(Material material, int slots) {
        return new GuiItemBuilder(material, slots);
    }

}
