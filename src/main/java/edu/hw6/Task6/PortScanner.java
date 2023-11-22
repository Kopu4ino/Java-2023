package edu.hw6.Task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortScanner {
    private PortScanner() {
    }

    private static final int MAX_PORT = 49151;
    private static final String TCP = "TCP";
    private static final String UDP = "UDP";
    @SuppressWarnings("MagicNumber")
    private static final Map<Integer, String> KNOWN_PORTS = new HashMap<Integer, String>() {{
        put(135, "EPMAP (TCP)");
        put(137, "Служба имен NetBIOS (UDP)");
        put(138, "Служба датаграмм NetBIOS (UDP)");
        put(139, "Служба сеансов NetBIOS (TCP)");
        put(445, "Microsoft-DS Active Directory (TCP)");
        put(843, "Adobe Flash (TCP)");
        put(3306, "MySQL Database");
        put(5432, "PostgreSQL Database");
        put(3389, "Remote Desktop Protocol (RDP)");
        put(27017, "MongoDB Database");
        put(1521, "Oracle Database");
    }};

    public static String[] scanPorts() {
        List<String> results = new ArrayList<>();

        for (int port = 0; port <= MAX_PORT; port++) {

            try (ServerSocket serverSocket = new ServerSocket(port)) {
                serverSocket.setReuseAddress(true);
            } catch (Exception e) {
                results.add(TCP + " " + port + " " + KNOWN_PORTS.getOrDefault(port, ""));
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                datagramSocket.setReuseAddress(true);
            } catch (Exception e) {
                results.add(UDP + " " + port + " " + KNOWN_PORTS.getOrDefault(port, ""));
            }
        }

        return results.toArray(new String[0]);
    }

    public static String[] scanPortsForTest(List<Integer> ports) {
        List<String> results = new ArrayList<>();

        for (int port : ports) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                serverSocket.setReuseAddress(true);
            } catch (Exception e) {
                results.add(TCP + " " + port + " " + KNOWN_PORTS.getOrDefault(port, ""));
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                datagramSocket.setReuseAddress(true);
            } catch (Exception e) {
                results.add(UDP + " " + port + " " + KNOWN_PORTS.getOrDefault(port, ""));
            }
        }

        return results.toArray(new String[0]);
    }
}
