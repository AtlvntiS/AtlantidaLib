package co.atlvntis.atlantida.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

public class BukkitSkullItem extends BukkitItem implements SkullItem {

    public BukkitSkullItem() {
        this(1);
    }

    public BukkitSkullItem(int amount) {
        super(Material.SKULL_ITEM, amount);
        super.setDurabiity(3);
    }

    @Override
    public void setOwner(String owner) {
        ((SkullMeta)this.meta).setOwner(owner);
    }

    @Override
    public String getOwner() {
        return ((SkullMeta)this.meta).getOwner();
    }
}
