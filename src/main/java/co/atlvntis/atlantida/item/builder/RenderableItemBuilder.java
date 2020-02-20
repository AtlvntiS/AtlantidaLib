package co.atlvntis.atlantida.item.builder;

import co.atlvntis.atlantida.item.GuiItem;
import co.atlvntis.atlantida.item.Item;
import co.atlvntis.atlantida.item.RenderableItem;
import co.atlvntis.atlantida.item.impl.BukkitGuiItem;
import co.atlvntis.atlantida.item.impl.BukkitRenderableItem;
import co.atlvntis.atlantida.item.impl.BukkitSkullItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.BiConsumer;

public class RenderableItemBuilder extends GuiItemBuilder {

    protected RenderableItemBuilder(Item item) {
        super(new BukkitRenderableItem(item));
    }

    protected RenderableItemBuilder() {
        this(new BukkitRenderableItem());
    }

    protected RenderableItemBuilder(Material material) {
        this(new BukkitRenderableItem(material));
    }

    protected RenderableItemBuilder(Material material, int amount) {
        this(new BukkitRenderableItem(material, amount));
    }

    public RenderableItemBuilder withRender(BiConsumer<RenderableItem, Player> biConsumer) {
        ((RenderableItem)item).onRender(biConsumer);
        return this;
    }

    public static RenderableItemBuilder of(Item item) {
        return new RenderableItemBuilder(item);
    }

    public static RenderableItemBuilder of(Material material) {
        return new RenderableItemBuilder(material);
    }

    public static RenderableItemBuilder of(Material material, int slots) {
        return new RenderableItemBuilder(material, slots);
    }


}
