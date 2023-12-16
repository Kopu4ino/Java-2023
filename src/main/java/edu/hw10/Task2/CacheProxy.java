package edu.hw10.Task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy {
    private CacheProxy() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheInvocationHandler(target)
        );
    }

    private static class CacheInvocationHandler implements InvocationHandler {
        private final Object target;
        private final Map<String, Object> cache = new HashMap<>();

        CacheInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cache.class)) {
                // Ключ кэша может быть основан на имени метода и аргументах
                String key = method.getName() + "-" + (args != null ? Arrays.toString(args) : "");
                return cache.computeIfAbsent(key, k -> {
                    try {
                        return method.invoke(target, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Failed to invoke method", e);
                    }
                });
            } else {
                return method.invoke(target, args);
            }
        }
    }
}
