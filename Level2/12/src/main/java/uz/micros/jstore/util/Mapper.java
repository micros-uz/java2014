package uz.micros.jstore.util;

import uz.micros.jstore.entity.blog.Post;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Mapper<T> {

    public Class getType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type ts[] = type.getActualTypeArguments();
        Class c = (Class) ts[0];

        return c;
    }

    public T map(Map<String, Object> map) {

        T res = null;

        try {
            res = (T)getType().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        List<Method> setters = findSetters(res.getClass());

        for (Method m : setters) {
            String name = m.getName().replace("set", "");

            Object val = map.get(name);
            try {
                m.invoke(res, val);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    private Class<T> getTypeClass() {
        return (Class<T>)Post.class;
    }

    private List<Method> findSetters(Class<?> c) {
        ArrayList<Method> list = new ArrayList<>();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods)
            if (isSetter(method))
                list.add(method);

        return list;
    }

    private boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) &&
                method.getReturnType().equals(void.class) &&
                method.getParameterTypes().length == 1 &&
                method.getName().matches("^set[A-Z].*");
    }
}
