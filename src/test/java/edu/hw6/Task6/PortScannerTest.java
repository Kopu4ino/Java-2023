package edu.hw6.Task6;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PortScannerTest {
    @Test
    void testScanPorts() {
        //Arrange
        List<Integer> testPorts = Arrays.asList(137, 138, 12334);

        //Act
        String[] results = PortScanner.scanPortsForTest(testPorts);

        //Assert
        assertThat(results).contains("UDP 137 Служба имен NetBIOS (UDP)");
        assertThat(results).contains("UDP 138 Служба датаграмм NetBIOS (UDP)");
        assertThat(results).doesNotContain("TCP 12334 ");
    }
}
