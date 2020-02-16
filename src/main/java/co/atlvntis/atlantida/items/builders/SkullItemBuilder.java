package co.atlvntis.atlantida.items.builders;

import co.atlvntis.atlantida.items.BukkitSkullItem;
import co.atlvntis.atlantida.items.SkullItem;

public class SkullItemBuilder extends ItemBuilder {

    private SkullItemBuilder() {
        this(1);
    }

    private SkullItemBuilder(int amount) {
        super(new BukkitSkullItem());
    }

    private SkullItemBuilder withOwner(String owner) {
        ((SkullItem)this.item).setOwner(owner);
        return this;
    }

    public static SkullItemBuilder of() {
        return new SkullItemBuilder();
    }

    public static SkullItemBuilder of(int amount) {
        return new SkullItemBuilder(amount);
    }

}
