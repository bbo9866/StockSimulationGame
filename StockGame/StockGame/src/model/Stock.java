// model/Stock.java
// ---------------------
package model;

public class Stock {
    private String name;
    private double price;
    private double changePercent;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
        this.changePercent = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        this.changePercent = ((newPrice - this.price) / this.price) * 100;
        this.price = newPrice;
    }

    public double getChangePercent() {
        return changePercent;
    }
}