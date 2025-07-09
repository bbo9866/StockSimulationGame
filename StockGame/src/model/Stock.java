// model/Stock.java
// ---------------------
package model;

public class Stock {
	private String name;
    private int price;
    private double rate;

    public Stock(String name, int price, double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public void updatePrice() {
        double change = 1 + (rate / 100);
        price = (int)(price * change);
        rate = (Math.random() * 20 - 10); // -10% ~ +10% 변동
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public double getRate() { return rate; }
}