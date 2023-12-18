package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private static final  String SERVER_ADDRESS = "localhost";
    private static final  Logger LOGGER = LogManager.getLogger();

    private int serverPort;

    public Client(int port) {
        this.serverPort = port;
    }

    public String sendRequest(String message) {
        try (Socket socket = new Socket(SERVER_ADDRESS, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(message);

            return in.readLine();

        } catch (IOException e) {
            return "Ошибка при обращении к серверу: " + e.getMessage();
        }
    }

    @SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            System.out.println("Введите ключевое слово для получения цитаты (для выхода введите 'exit'):");

            while ((userInput = stdIn.readLine()) != null) {
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                out.println(userInput);
                System.out.println("Ответ сервера: " + in.readLine());
            }

        } catch (UnknownHostException e) {
            LOGGER.error("Не удается подключиться к хосту: " + SERVER_ADDRESS);
        } catch (IOException e) {
            LOGGER.error("Не удается получить ввод/вывод для соединения с " + SERVER_ADDRESS);
        }
    }
}
