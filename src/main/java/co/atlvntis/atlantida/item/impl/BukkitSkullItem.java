package co.atlvntis.atlantida.item.impl;

import co.atlvntis.atlantida.item.Item;
import co.atlvntis.atlantida.item.SkullItem;
import co.atlvntis.atlantida.util.Reflections;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;


public class BukkitSkullItem extends BukkitItem implements SkullItem {

    public static final Class<?> GAME_PROFILE_CLASS = Reflections.getClass("com.mojang.authlib.GameProfile");
    //public static final Class<?> PROPERTY_MAP_CLASS = Reflections.getClass("com.mojang.authlib.properties.PropertyMap");
    public static final Class<?> PROPERTY_CLASS = Reflections.getClass("com.mojang.authlib.properties.Property");

    public BukkitSkullItem(Item item) {
        super(item);
    }

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

    @Override
    public void setTexture(String texture) {
        try {

            SkullMeta skullMeta = (SkullMeta) this.meta;

            Object gameProfile = Reflections.getConstructor(GAME_PROFILE_CLASS, UUID.class, String.class).newInstance(UUID.randomUUID(), null);
            Object property = Reflections.getConstructor(PROPERTY_CLASS, String.class, String.class).newInstance("textures", texture);

            Object propertyMap = Reflections.getMethod(gameProfile, "getProperties").invoke(gameProfile);
            Reflections.getMethod(propertyMap,"removeAll", String.class).invoke(propertyMap, "textures");
            Reflections.getMethod(propertyMap,"put", String.class, PROPERTY_CLASS).invoke(propertyMap, "textures", property);

            Field gameProfileField = Reflections.getField(skullMeta, "profile");
            Reflections.setField(skullMeta, gameProfileField, gameProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
