package edu.hw9.Task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsDataTest {
    @Test
    public void testAddData() {
        //Arrange
        StatisticsData data = new StatisticsData();

        //Act
        data.addData(new double[] {1.0, 2.0});
        data.addData(new double[] {3.0, 4.0});

        //Assert
        assertThat(data.getSum()).isEqualTo(10.0);
        assertThat(data.getMin()).isEqualTo(1.0);
        assertThat(data.getMax()).isEqualTo(4.0);
        assertThat(data.getAverage()).isEqualTo(2.5);
    }
}
