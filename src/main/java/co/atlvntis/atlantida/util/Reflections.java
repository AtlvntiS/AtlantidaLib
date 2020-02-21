package co.atlvntis.atlantida.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflections {

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... params) {
        try {
            return clazz.getConstructor(params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> getClass(String classPath) {
        try {
            return Class.forName(classPath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Method getMethod(Object obj, String name, Class<?>... params) {
        return getMethod(obj.getClass(), name, params);
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... params) {
        try {
            return clazz.getMethod(name, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Field getField(Object obj, String name) {
        return getField(obj.getClass(), name);
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setField(Object object, Field field, Object value) {
        try {
            if(!field.isAccessible()) field.setAccessible(true);
            field.set(object, field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
