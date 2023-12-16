package edu.hw10.Task1;

import edu.hw10.Task1.ForTests.TestAnnotatedPOJO;
import edu.hw10.Task1.ForTests.TestFactoryClass;
import edu.hw10.Task1.ForTests.TestRecord;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RandomObjectGeneratorTest {

    @Test
    public void testAnnotatedPOJOGeneration() {
        // Arrange
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // Act
        TestAnnotatedPOJO generatedAnnotatedPOJO = rog.nextObject(TestAnnotatedPOJO.class);

        // Assert
        assertThat(generatedAnnotatedPOJO).isNotNull();
        assertThat(generatedAnnotatedPOJO.getNumber()).isBetween(10, 100);
    }

    @Test
    public void testRandomRecordGeneration() {
        // Arrange
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // Act
        TestRecord generatedRecord = rog.nextObject(TestRecord.class);

        // Assert
        assertThat(generatedRecord).isNotNull();
        assertThat(generatedRecord.number()).isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertThat(generatedRecord.text()).isNotNull().isInstanceOf(String.class);
    }

    @Test
    public void testFactoryMethodGeneration() {
        // Arrange
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // Act
        TestFactoryClass generatedObject = rog.nextObject(TestFactoryClass.class, "create");

        // Assert
        assertThat(generatedObject).isNotNull();
        assertThat(generatedObject.getNumber()).isBetween(10, Integer.MAX_VALUE);
        assertThat(generatedObject.getText()).isNotNull().isInstanceOf(String.class);
    }


}
