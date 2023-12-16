package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Random;

public class RandomObjectGenerator {

    private final Random random = new Random();

    public <T> T nextObject(Class<T> clazz) {
        boolean isRecord = clazz.isRecord();

        try {
            Constructor<?> constructor;
            Object[] args;

            if (isRecord) {
                constructor = clazz.getDeclaredConstructor(Arrays.stream(clazz.getRecordComponents())
                    .map(RecordComponent::getType)
                    .toArray(Class[]::new));

                args = Arrays.stream(clazz.getRecordComponents())
                    .map(RecordComponent::getType)
                    .map(this::generateRandomValueFromType)
                    .toArray();
            } else {
                Constructor<?>[] constructors = clazz.getDeclaredConstructors();
                constructor = Arrays.stream(constructors)
                    .filter(c -> c.getParameterCount() > 0)
                    .filter(c -> c.canAccess(null))
                    .findFirst()
                    .orElseThrow(NoSuchMethodException::new);

                args = Arrays.stream(constructor.getParameters())
                    .map(this::generateRandomValue)
                    .toArray();
            }

            return clazz.cast(constructor.newInstance(args));
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать экземпляр класса " + clazz.getName(), e);
        }
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        try {
            Method[] methods = clazz.getDeclaredMethods();
            Method factoryMethod = Arrays.stream(methods)
                .filter(m -> m.getName().equals(factoryMethodName) && Modifier.isStatic(m.getModifiers()))
                .findFirst()
                .orElseThrow(NoSuchMethodException::new);

            Object[] args = Arrays.stream(factoryMethod.getParameters())
                .map(this::generateRandomValue)
                .toArray();

            return clazz.cast(factoryMethod.invoke(null, args));
        } catch (Exception e) {
            throw new RuntimeException(
                "Не удалось создать экземпляр класа " + clazz.getName() + " с помощью фабричного метода "
                    + factoryMethodName, e);
        }
    }

    private Object generateRandomValue(Parameter parameter) {
        Class<?> type = parameter.getType();
        Object value;

        if (type.equals(int.class) || type.equals(Integer.class)) {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;

            if (parameter.isAnnotationPresent(Min.class)) {
                min = parameter.getAnnotation(Min.class).value();
            }
            if (parameter.isAnnotationPresent(Max.class)) {
                max = parameter.getAnnotation(Max.class).value();
            }

            value = random.nextInt(max - min + 1) + min;
        } else if (type.equals(String.class)) {
            value = generateRandomString();
        } else {
            throw new IllegalArgumentException("Не поддерживаемый тип: " + type);
        }

        if (parameter.isAnnotationPresent(NotNull.class) && value == null) {
            throw new NullPointerException("Поле " + parameter.getName() + " не может быть null");
        }

        return value;
    }

    private Object generateRandomValueFromType(Class<?> type) {
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return random.nextInt();
        } else if (type.equals(String.class)) {
            return generateRandomString();
        }

        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int LEN = 10;

        StringBuilder result = new StringBuilder(LEN);
        for (int i = 0; i < LEN; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }

}
