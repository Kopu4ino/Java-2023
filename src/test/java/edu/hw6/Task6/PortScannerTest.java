package edu.hw6.Task6;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PortScannerTest {
    @Test
    void testScanPorts() {
        List<Integer> testPorts = Arrays.asList(137, 5432, 12334);

        String[] results = PortScanner.scanPortsForTest(testPorts);

        assertThat(results).contains("UDP 137 Служба имен NetBIOS (UDP)");
        assertThat(results).contains("TCP 5432 PostgreSQL Database");

        assertThat(results).doesNotContain("TCP 12334 ");
    }
}
