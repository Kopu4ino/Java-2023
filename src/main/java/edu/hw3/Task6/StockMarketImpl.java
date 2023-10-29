package edu.hw3.Task6;

import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private PriorityQueue<Stock> stocks;

    public StockMarketImpl() {
        stocks = new PriorityQueue<>();
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

}
