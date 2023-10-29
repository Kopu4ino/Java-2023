package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketImpl;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    void StockMarketTest() {
        Stock apple = new Stock("APL", 300);
        Stock tesla = new Stock("TSL", 150);
        Stock revolut = new Stock("REV", 100.5);

        StockMarketImpl market = new StockMarketImpl();

        market.add(revolut);
        market.add(tesla);
        market.add(tesla);
        market.add(apple);
        market.add(tesla);

        assertThat(market.mostValuableStock().getName()).isEqualTo("APL");

        market.remove(apple);

        assertThat(market.mostValuableStock().getName()).isEqualTo("TSL");
    }

}
