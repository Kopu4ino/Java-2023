package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    public void testToStringMethod() throws IllegalAccessException, InstantiationException {
        // Arrange
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make();

        Class<?> dynamicClass = dynamicType.load(getClass().getClassLoader())
            .getLoaded();

        // Act
        Object instance = dynamicClass.newInstance();
        String result = instance.toString();

        // Assert
        Assertions.assertThat(result).isEqualTo("Hello, ByteBuddy!");
    }
}
