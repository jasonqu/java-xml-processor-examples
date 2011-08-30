package example.model.original;

public class Customer {
	private String name;
	private String address;

	public Customer() {
	}
	
	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + "]";
	}
}
