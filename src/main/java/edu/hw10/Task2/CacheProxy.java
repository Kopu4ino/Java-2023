package edu.hw10.Task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static class CacheInvocationHandler implements InvocationHandler {
        private final static Logger LOGGER = LogManager.getLogger();
        private final Object target;
        private final Map<String, Object> cache = new HashMap<>();
        private final String tmpDirName = "java.io.tmpdir";
        private final String newDirName = "cache";
        private final String extensionName = ".cache";

        public CacheInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cache.class)) {
                Cache cacheAnnotation = method.getAnnotation(Cache.class);
                String key = method.getName() + "-" + (args != null ? Arrays.toString(args) : "");

                if (cacheAnnotation.persist()) {
                    Object cachedResult = deserializeResultFromFile(key);
                    if (cachedResult != null) {
                        return cachedResult;
                    }
                }

                return cache.computeIfAbsent(key, k -> {
                    try {
                        Object result = method.invoke(target, args);
                        if (cacheAnnotation.persist()) {
                            // Сериализация результата в файл
                            serializeResultToFile(key, result);
                        }
                        return result;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Failed to invoke method", e);
                    }
                });
            } else {
                return method.invoke(target, args);
            }
        }

        private void serializeResultToFile(String key, Object result) {
            try {
                File cacheDir = new File(System.getProperty(tmpDirName), newDirName);
                if (!cacheDir.exists()) {
                    cacheDir.mkdir();
                }
                File cacheFile = new File(cacheDir, key.hashCode() + extensionName);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
                    oos.writeObject(result);
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }

        private Object deserializeResultFromFile(String key) {
            File cacheFile =
                new File(new File(System.getProperty(tmpDirName), newDirName), key.hashCode() + extensionName);
            if (cacheFile.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
                    return ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.error(e);
                }
            }
            return null;
        }
    }
}
