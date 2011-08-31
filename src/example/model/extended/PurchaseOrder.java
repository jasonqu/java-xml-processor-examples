package example.model.extended;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseOrder {
	private Customer customer;
	private Date date;
	private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
	private Shipper shipper;

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

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [customer=" + customer + ", date=" + date
				+ ", lineItems=" + lineItems + "]";
	}
}
