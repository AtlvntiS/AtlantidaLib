package co.atlvntis.atlantida.services;

import co.atlvntis.atlantida.AtlantidaPlugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

public class Services {

    public static <T> void  provide(Class<T> clazz, T provider) {
        provide(clazz, provider, ServicePriority.Normal);
    }

    public static <T> void provide(Class<T> clazz, T provider, ServicePriority servicePriority) {
        ServicesManager servicesManager =  AtlantidaPlugin.getInstance().getServer().getServicesManager();
        servicesManager.register(clazz, provider, AtlantidaPlugin.getInstance(), servicePriority);
    }

    public static <T> T getProvider(Class<T> provider) {
        ServicesManager servicesManager =  AtlantidaPlugin.getInstance().getServer().getServicesManager();
        if(!servicesManager.isProvidedFor(provider)) {
            throw new IllegalStateException(provider.getName() + " não está registrado nos serviços.");
        }
        return servicesManager.getRegistration(provider).getProvider();
    }

}
