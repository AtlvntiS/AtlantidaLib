package co.atlvntis.atlantida.config;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.abstractions.PluginDependent;
import com.google.common.base.CaseFormat;
import com.sun.tools.javac.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class ConfigHolder extends PluginDependent<AtlantidaPlugin> {

    public ConfigHolder(AtlantidaPlugin plugin) {
        super(plugin);
    }

    public void load() {
        try {

            Class holderClass = this.getClass();

            if (!holderClass.isAnnotationPresent(Holder.class))
                throw new IllegalStateException(this.getClass().getSimpleName() + " doesn't specify the holder.");

            Holder configHolder = (Holder) holderClass.getAnnotation(Holder.class);

            File file = new File(plugin.getDataFolder(), configHolder.path());

            file.mkdirs();

            if (!file.exists()) {
                if(!file.createNewFile()) throw new IOException(configHolder.path() + " failed to create.");
            }

            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            for(Field field : holderClass.getFields()) {
                parseField(field, configuration);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseField(Field field, FileConfiguration configuration) throws IllegalAccessException {

        if(!field.isAnnotationPresent(HolderItem.class)) return;

        HolderItem holderItem = (HolderItem) field.getAnnotation(HolderItem.class);
        String itemName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
        String parsedPath = (holderItem.path().isEmpty() ? "" : holderItem.path() + ".") + itemName;

        if(configuration.get(parsedPath) != null) {

            if(field.getType() == String.class) {
                field.set(this, configuration.getString(parsedPath));
            } else if (field.getType() == int.class || field.getType() == Integer.class) {
                field.set(this, configuration.getInt(parsedPath));
            } else if (field.getType() == double.class || field.getType() == Double.class) {
                field.set(this, configuration.getDouble(parsedPath));
            } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                field.set(this, configuration.getBoolean(parsedPath));
            } else if (field.getType() == List.class && ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0] == String.class) {
                field.set(this, configuration.getStringList(parsedPath));
            }

        } else {

            configuration.set(parsedPath, field.get(this));

        }

    }

}
