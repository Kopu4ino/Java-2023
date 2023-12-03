package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import org.junit.jupiter.api.AfterAll;
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
        server = new Server(SERVER_PORT, MAX_CONNECTIONS);
        serverThread = new Thread(() -> server.startServer());
        serverThread.start();
    }

    @AfterAll
    public static void tearDown() {
        server.stopServer();
        serverThread.interrupt();
    }

    @Test
    public void testServerResponse() {
        Client client = new Client(SERVER_PORT);
        String response = client.sendRequest("личности");

        assertThat(response).contains("Не переходи на личности там, где их нет");
    }

    @Test
    public void testSeveralClient() {
        Client client1 = new Client(SERVER_PORT);
        Client client2 = new Client(SERVER_PORT);

        String response1 = client1.sendRequest("личности");
        String response2 = client2.sendRequest("интеллект");

        assertThat(response1).contains("Не переходи на личности там, где их нет");
        assertThat(response2).contains("Чем ниже интеллект, тем громче оскорбления");

        // в выводе логера видно, что запросы обработались в разных потоках
    }
}
