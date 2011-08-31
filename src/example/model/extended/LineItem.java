package example.model.extended;

public class LineItem {
	private String description;
	private double perUnitOunces;
	private double price;
	private int quantity;

	public LineItem() {
	}
	
	public LineItem(String description, double perUnitOunces, double price, int quantity) {
		this.description = description;
		this.perUnitOunces = perUnitOunces;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPerUnitOunces() {
		return perUnitOunces;
	}

	public void setPerUnitOunces(double perUnitOunces) {
		this.perUnitOunces = perUnitOunces;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "LineItem [description=" + description + ", perUnitOunces="
				+ perUnitOunces + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
}
