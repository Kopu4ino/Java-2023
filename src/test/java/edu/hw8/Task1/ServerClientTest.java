package edu.hw8.Task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ServerClientTest {
    private static final int SERVER_PORT = 12345;
    private static final int MAX_CONNECTIONS = 2;

    private static Server server;
    private static Thread serverThread;

    @BeforeAll
    public static void setUp() {
        //Arrange
        server = new Server(SERVER_PORT, MAX_CONNECTIONS);
        serverThread = new Thread(() -> server.startServer());
        serverThread.start();
    }

    @AfterAll
    public static void tearDown() {
        //Teardown
        server.stopServer();
        serverThread.interrupt();
    }

    @Test
    public void testServerResponse() {
        //Arrange
        Client client = new Client(SERVER_PORT);

        //Act
        String response = client.sendRequest("личности");

        //Assert
        assertThat(response).contains("Не переходи на личности там, где их нет");
    }

    @Test
    public void testSeveralClient() {
        //Arrange
        Client client1 = new Client(SERVER_PORT);
        Client client2 = new Client(SERVER_PORT);

        //Act
        String response1 = client1.sendRequest("личности");
        String response2 = client2.sendRequest("интеллект");

        //Assert
        assertThat(response1).contains("Не переходи на личности там, где их нет");
        assertThat(response2).contains("Чем ниже интеллект, тем громче оскорбления");

        // в выводе логера видно, что запросы обработались в разных потоках
    }
}
