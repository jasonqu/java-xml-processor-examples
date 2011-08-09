package example.model.original;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseOrder {
	private Customer customer;
	private Date date;
	private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setLineItems(ArrayList<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public ArrayList<LineItem> getLineItems() {
		return lineItems;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [customer=" + customer + ", date=" + date
				+ ", lineItems=" + lineItems + "]";
	}
}
