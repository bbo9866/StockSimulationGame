// logic/Market.java
// ---------------------
package logic;

import model.Stock;
import java.util.Random;

public class Market {
    private Stock[] stocks;

    public Market() {
        stocks = new Stock[] {
            new Stock("삼성전자", 65000),
            new Stock("SK하이닉스", 120000),
            new Stock("네이버", 190000),
            new Stock("카카오", 70000)
        };
    }

    public void fluctuatePrices() {
        Random rand = new Random();
        for (Stock stock : stocks) {
            double change = (rand.nextDouble() * 0.1 - 0.05); // -5% ~ +5%
            stock.setPrice(stock.getPrice() * (1 + change));
        }
    }

    public Stock[] getStocks() {
        return stocks;
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < stocks.length;
    }

    public Stock getStockByName(String name) {
        for (Stock s : stocks) {
            if (s.getName().equals(name)) return s;
        }
        return null;
    }
}