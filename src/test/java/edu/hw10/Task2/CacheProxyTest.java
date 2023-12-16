package edu.hw10.Task2;

import edu.hw10.Task2.ForTests.TestInterface;
import edu.hw10.Task2.ForTests.TestInterfaceImpl;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CacheProxyTest {

    @BeforeEach
    public void clearCache() {
        File cacheDir = new File(System.getProperty("java.io.tmpdir"), "cache");
        deleteDirectory(cacheDir);
    }

    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        boolean isDeleted = file.delete();
                        if (!isDeleted) {
                            System.out.println("Не удалось удалить файл: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }


    @Test
    public void testMethodWithCache() {
        // Arrange
        TestInterfaceImpl original = new TestInterfaceImpl();
        TestInterface proxy = CacheProxy.create(original, TestInterface.class);

        String input = "test";
        String expectedResult = "Result: test";

        // Act
        String firstCallResult = proxy.methodWithCache(input);
        String secondCallResult = proxy.methodWithCache(input);

        // Assert
        assertThat(firstCallResult).isEqualTo(expectedResult);
        assertThat(secondCallResult).isEqualTo(expectedResult);

        // Проверка, что исходный метод вызван только один раз
        assertThat(original.getCallCount()).isEqualTo(1);
    }

    @Test
    public void testMethodWithMemoryCache() {
        // Arrange
        TestInterfaceImpl original = new TestInterfaceImpl();
        TestInterface proxy = CacheProxy.create(original, TestInterface.class);

        int input = 5;
        int expectedResult = 25;

        // Act
        int firstCallResult = proxy.methodWithMemoryCache(input);
        int secondCallResult = proxy.methodWithMemoryCache(input);

        // Assert
        assertThat(firstCallResult).isEqualTo(expectedResult);
        assertThat(secondCallResult).isEqualTo(expectedResult);

        // Проверка, что исходный метод вызван только один раз
        assertThat(original.getCallCount()).isEqualTo(1);
    }
}
