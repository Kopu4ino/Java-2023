package edu.hw3.Task6;

public class Stock implements Comparable<Stock> {
    private String name;
    private double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Stock stock) {
        return Double.compare(stock.price, this.price);
    }
}
