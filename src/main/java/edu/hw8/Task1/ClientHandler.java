package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private final static Logger LOGGER = LogManager.getLogger();

    private static Map<String, String> quotes = new HashMap<>();

    static {
        quotes.put(
            "личности",
            "Не переходи на личности там, где их нет"
        );
        quotes.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        quotes.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        quotes.put(
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления"
        );
    }

    ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String threadName = Thread.currentThread().getName();

                String quote = getQuote(inputLine);
                out.println(quote);

                LOGGER.info("Запрос '" + inputLine + "' обработан в потоке " + threadName);
            }
        } catch (IOException e) {
            LOGGER.error("Исключение в обработчике клиента: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                LOGGER.error("Не удалось закрыть сокет: " + e.getMessage());
            }
        }
    }

    private String getQuote(String keyword) {
        return quotes.getOrDefault(keyword.toLowerCase(), "Цитата не найдена");
    }
}
