package com.arslansoft;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class IoC {

    private static Map<Class<?>, Class<?>> ctx = new HashMap<Class<?>, Class<?>>();

    public static void register(Class<?> intf, Class<?> impl){
        ctx.put(intf, impl);
    }

    public static <T> T get(Class<T> key) {
        if (ctx.containsKey(key)){
            Object res = build(ctx.get(key));

            return (T)res;
        }else
            throw new IllegalArgumentException("Wrong key");
    }

    /// returns an instance of aClass class with all dependencies injected
    private static Object build(Class<?> aClass) {

        Constructor[] ctors = aClass.getDeclaredConstructors();

        assert ctors.length < 1;

        Constructor ctor = ctors[0];

        Class[] types = ctor.getParameterTypes();


        Object[] params = new Object[types.length];

        for (int k = 0; k < params.length; k++) {
            params[k] = get(types[k]);
        }

        try {
            return ctor.newInstance(params);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Initialization error");
    }
}
