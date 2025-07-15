// logic/Market.java
// ---------------------
package model;

import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Player {

	private String name;
	private int cash = 1000000;
	private Map<String, Integer> holdings = new HashMap<>();
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getCash() {
		return cash;
	}

	public void addCash(int amount) {
		cash += amount;
	}

	public boolean deductCash(int amount) {
		if (cash >= amount) {
			cash -= amount;
			return true;
		}
		return false;
	}

	public void buy(String stock, int qty) {
		holdings.put(stock, holdings.getOrDefault(stock, 0) + qty);
	}

	public boolean sell(String stock, int qty) {
		int owned = holdings.getOrDefault(stock, 0);
		
		if (owned >= qty) {
			holdings.put(stock, owned - qty);
			return true;
		}
		
		return false;
	}

	public Map<String, Integer> getHoldings() {
		return holdings;
	}

	public int evaluate(Map<String, Stock> stocks) {
		int total = cash;
		
		for (String name : holdings.keySet()) {
			total += stocks.get(name).getPrice() * holdings.get(name);
		}
		
		return total;
	}
	
}