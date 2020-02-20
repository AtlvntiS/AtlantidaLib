package co.atlvntis.atlantida.item.builder;

import co.atlvntis.atlantida.item.impl.BukkitSkullItem;
import co.atlvntis.atlantida.item.Item;
import co.atlvntis.atlantida.item.SkullItem;

public class SkullItemBuilder extends ItemBuilder {

    protected SkullItemBuilder(Item item) {
        super(new BukkitSkullItem(item));
    }

    protected SkullItemBuilder() {
        this(1);
    }

    protected SkullItemBuilder(int amount) {
        super(new BukkitSkullItem(amount));
    }

    public SkullItemBuilder withOwner(String owner) {
        ((SkullItem)this.item).setOwner(owner);
        return this;
    }

    public static SkullItemBuilder of(Item item) {
        return new SkullItemBuilder(item);
    }

    public static SkullItemBuilder of() {
        return new SkullItemBuilder();
    }

    public static SkullItemBuilder of(int amount) {
        return new SkullItemBuilder(amount);
    }

}
