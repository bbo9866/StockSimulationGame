// logic/Portfolio.java
// ---------------------
package logic;

import model.Stock;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private double cash;
    private Map<String, Integer> ownedStocks;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.ownedStocks = new HashMap<>();
    }

    public boolean buy(Stock stock, int qty) {
        double cost = stock.getPrice() * qty;
        if (cash >= cost) {
            cash -= cost;
            ownedStocks.put(stock.getName(), ownedStocks.getOrDefault(stock.getName(), 0) + qty);
            return true;
        }
        return false;
    }

    public boolean sell(String name, int qty, logic.Market market) {
        if (!ownedStocks.containsKey(name) || ownedStocks.get(name) < qty) return false;
        Stock stock = market.getStockByName(name);
        if (stock == null) return false;
        double revenue = stock.getPrice() * qty;
        cash += revenue;
        ownedStocks.put(name, ownedStocks.get(name) - qty);
        return true;
    }

    public double getTotalAssetValue(logic.Market market) {
        double total = cash;
        for (Map.Entry<String, Integer> entry : ownedStocks.entrySet()) {
            Stock s = market.getStockByName(entry.getKey());
            if (s != null) total += s.getPrice() * entry.getValue();
        }
        return total;
    }

    public void addCash(double amount) {
        cash += amount;
    }

    public Map<String, Integer> getOwnedStocks() {
        return ownedStocks;
    }

	public String getPlayerName() {
		// TODO Auto-generated method stub
		return null;
	}
}
