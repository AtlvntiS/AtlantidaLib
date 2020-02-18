package co.atlvntis.atlantida.config;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.PluginDependent;
import com.google.common.base.CaseFormat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

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
            if(!file.mkdirs() || (!file.exists() && !file.createNewFile())) throw new IOException("Something wrong happened when trying to create the file/filedirs.");

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

        Object value = configuration.get(parsedPath);

        if(value != null) {

            Class<?> type = field.getType();

            if(type == String.class || type == int.class || type == double.class || type == boolean.class ||
                    (field.getType() == List.class && ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0] == String.class)){
                field.set(this, value);
            }

        } else {

            configuration.set(parsedPath, field.get(this));

        }

    }

}
