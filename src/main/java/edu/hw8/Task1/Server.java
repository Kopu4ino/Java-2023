package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private int port;
    private final ExecutorService pool;
    private volatile boolean running = true;
    private final static Logger LOGGER = LogManager.getLogger();

    public Server(int port, int maxConnections) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(maxConnections);
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("QuoteServer запущен и слушает порт " + port);

            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    pool.execute(new ClientHandler(clientSocket));
                } catch (IOException e) {
                    LOGGER.error("Исключение при соединении с клиентом: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            LOGGER.error("Не удалось запустить сервер: " + e.getMessage());
        }
    }

    public void stopServer() {
        this.running = false;
    }
}
