package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Stock {
	private String name;
	private int price;
	private double rate;

	public Stock(String name, int price) {
		this.name = name;
		this.price = price;
		this.rate = Math.random() * 20 - 10; // -10% ~ +10%
	}

	public void updatePrice() {
		double change = 1 + (rate / 100);
		price = (int) (price * change);
		rate = Math.random() * 20 - 10;
	}
	
}