package example.model.extended;

public class Shipper {
	private String name;
	private double perOunceRate;
	
	public Shipper(){
	}
	
	public Shipper(String name, double perOunceRate) {
		this.name = name;
		this.perOunceRate = perOunceRate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPerOunceRate() {
		return perOunceRate;
	}
	
	public void setPerOunceRate(double perOunceRate) {
		this.perOunceRate = perOunceRate;
	}
	
	@Override
	public String toString() {
		return "Shipper [name=" + name + ", perOunceRate=" + perOunceRate + "]";
	}
}
