package co.atlvntis.atlantida.inventory;

import co.atlvntis.atlantida.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

@Getter
@Builder
public class ClickableItem {

    private final Item item;
    private Action action = player -> {};

    private ClickableItem(Item item) {
        this.item = item;
    }

    public ItemStack getItemStack() {
        return item.get();
    }

    public static ClickableItem of(Item item) {
        return new ClickableItem(item);
    }

}
