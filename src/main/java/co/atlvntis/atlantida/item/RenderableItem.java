package co.atlvntis.atlantida.item;

import org.bukkit.entity.Player;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface RenderableItem extends GuiItem {
    
    void onRender(BiConsumer<RenderableItem, Player> biConsumer);
    void render(Player player);
    
}
