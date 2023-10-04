package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task0 {
    private Task0() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Главный метод приложения. Запускает выполнение программы.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        LOGGER.info("Hello world");
    }
}
