package edu.hw6.Task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class PortScanner {
    private PortScanner() {
    }

    private static final int MAX_PORT = 49151;
    private static final String TCP = "TCP";
    private static final String UDP = "UDP";

    public static String[] scanPorts() {
        List<String> results = new ArrayList<>();

        for (int port = 0; port <= MAX_PORT; port++) {
            boolean isKnownPort = false;

            try (ServerSocket serverSocket = new ServerSocket(port)) {
                serverSocket.setReuseAddress(true);
            } catch (Exception e) {
                String description = KnownPort.getDescriptionByPort(port, KnownPort.PortType.TCP);
                if (!description.isEmpty()) {
                    results.add(String.format("%s %d %s", TCP, port, description));
                    isKnownPort = true;
                }
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                datagramSocket.setReuseAddress(true);
            } catch (Exception e) {
                if (!isKnownPort) {
                    String description = KnownPort.getDescriptionByPort(port, KnownPort.PortType.UDP);
                    if (!description.isEmpty()) {
                        results.add(String.format("%s %d %s", UDP, port, description));
                    }
                }
            }
        }

        return results.toArray(new String[0]);
    }

    public static String[] scanPortsForTest(List<Integer> ports) {
        List<String> results = new ArrayList<>();

        for (int port : ports) {
            boolean isKnownPort = false;

            try (ServerSocket serverSocket = new ServerSocket(port)) {
                serverSocket.setReuseAddress(true);
            } catch (Exception e) {
                String description = KnownPort.getDescriptionByPort(port, KnownPort.PortType.TCP);
                if (!description.isEmpty()) {
                    results.add(String.format("%s %d %s", TCP, port, description));
                    isKnownPort = true;
                }
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                datagramSocket.setReuseAddress(true);
            } catch (Exception e) {
                if (!isKnownPort) {
                    String description = KnownPort.getDescriptionByPort(port, KnownPort.PortType.UDP);
                    if (!description.isEmpty()) {
                        results.add(String.format("%s %d %s", UDP, port, description));
                    }
                }
            }
        }

        return results.toArray(new String[0]);
    }
}
