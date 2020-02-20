package co.atlvntis.atlantida.inventory;

import co.atlvntis.atlantida.item.Item;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.function.BiConsumer;

@Getter
public class GuiItem<T extends Item> {

    private final T item;

    private T copy;
    private int slot = 0;
    private GuiAction action = player -> {};
    private BiConsumer<T, Player> biConsumer = (i, p) -> {};

    public GuiItem(T item) {
        this.item = item;
        this.copy = item;
    }

    public GuiItem<T> setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public GuiItem<T> setAction(GuiAction action) {
        this.action = action;
        return this;
    }

    public GuiItem<T> setRender(BiConsumer<T, Player> biConsumer) {
        this.biConsumer = biConsumer;
        return this;
    }

    public T buildItem(Player player) {
        biConsumer.accept(copy, player);
        return copy;
    }

    public void reset() {
        this.copy = item;
    }

}
