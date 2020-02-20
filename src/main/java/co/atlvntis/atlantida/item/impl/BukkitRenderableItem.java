package co.atlvntis.atlantida.item.impl;

import co.atlvntis.atlantida.item.Item;
import co.atlvntis.atlantida.item.RenderableItem;
import lombok.SneakyThrows;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class BukkitRenderableItem extends BukkitGuiItem implements RenderableItem {

    private RenderableItem cloneItem;
    private BiConsumer<RenderableItem, Player> biConsumer = (i, p) -> {};

    @SneakyThrows
    public BukkitRenderableItem(Item item) {
        super(item);
        this.cloneItem = (RenderableItem) this.clone();
    }

    public BukkitRenderableItem() {
        super();
        this.cloneItem = new BukkitRenderableItem();
    }

    public BukkitRenderableItem(Material material) {
        super(material);
        this.cloneItem = new BukkitRenderableItem(material);
    }

    public BukkitRenderableItem(Material material, int amount) {
        super(material, amount);
        this.cloneItem = new BukkitRenderableItem(material, amount);
    }

    @Override
    public void onRender(BiConsumer<RenderableItem, Player> biConsumer) {
        this.biConsumer = biConsumer;
    }

    @Override
    public void render(Player player) {
        this.cloneItem = (RenderableItem) SerializationUtils.clone(this);
        biConsumer.accept(cloneItem, player);
    }

    @Override
    public ItemStack get() {
        return cloneItem.get();
    }
}
