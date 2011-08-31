package example.dom;

import static example.dom.Util.createTextTag;
import static example.dom.Util.getNodeValue;
import static example.dom.Util.printDocument;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import example.model.extended.Customer;
import example.model.extended.LineItem;
import example.model.extended.PurchaseOrder;
import example.model.extended.Shipper;

/**
 * show the example of step 5
 */
public class DomExampleReadingAndWritingModified {

	/**
	 * parse the xml document into PurchaseOrder object
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private static PurchaseOrder getPurchaseOrderFromDocument(String file)
			throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new File(file));

		PurchaseOrder po = new PurchaseOrder();
		Customer c = new Customer();

		Element root = doc.getDocumentElement();
		Element customer = (Element) root.getElementsByTagName("customer")
				.item(0);
		c.setName(getNodeValue("name", customer));
		c.setAddress(getNodeValue("address", customer));
		c.setEmail(customer.getAttribute("email"));
		po.setCustomer(c);

		String date = getNodeValue("date", root);
		po.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));

		NodeList list = doc.getElementsByTagName("line-item");
		for (int i = 0; i < list.getLength(); i++) {
			Element node = (Element) list.item(i);
			LineItem li = new LineItem();
			li.setDescription(getNodeValue("description", node));
			li.setPerUnitOunces(Double.parseDouble(getNodeValue(
					"per-unit-ounces", node)));
			li.setPrice(Double.parseDouble(getNodeValue("price", node)));
			li.setPrice(Integer.parseInt(getNodeValue("quantity", node)));
			po.getLineItems().add(li);
		}

		Element shipper = (Element) root.getElementsByTagName("shipper")
				.item(0);
		Shipper s = new Shipper();
		s.setName(getNodeValue("name", shipper));
		s.setPerOunceRate(Double.parseDouble(getNodeValue("per-ounce-rate",
				shipper)));
		po.setShipper(s);
		return po;
	}

	/**
	 * parse the PurchaseOrder object into an xml document
	 * 
	 * @param po
	 * @return
	 * @throws ParserConfigurationException
	 */
	private static Document writePurchaseOrderToDocument(PurchaseOrder po)
			throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element rootElement = doc.createElement("PurchaseOrder");
		doc.appendChild(rootElement);

		Element customer = doc.createElement("customer");
		rootElement.appendChild(customer);

		createTextTag(doc, customer, "name", po.getCustomer().getName());
		createTextTag(doc, customer, "city", po.getCustomer().getAddress());
		customer.setAttribute("email", po.getCustomer().getEmail());

		createTextTag(doc, rootElement, "date", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(po.getDate()));

		for (LineItem li : po.getLineItems()) {
			Element lineItem = doc.createElement("lineItem");
			createTextTag(doc, lineItem, "description", li.getDescription());
			createTextTag(doc, lineItem, "ouncesPerUnit",
					Double.toString(li.getPerUnitOunces()));
			createTextTag(doc, lineItem, "price",
					Double.toString(li.getPrice()));
			createTextTag(doc, lineItem, "quantity",
					Integer.toString(li.getQuantity()));
			rootElement.appendChild(lineItem);
		}

		Element shipper = doc.createElement("shipper");
		rootElement.appendChild(shipper);
		createTextTag(doc, shipper, "name", po.getShipper().getName());
		createTextTag(doc, shipper, "ratePerOunce",
				Double.toString(po.getShipper().getPerOunceRate()));

		return doc;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out
				.println("----------- get the object from xml file -----------");
		PurchaseOrder po = getPurchaseOrderFromDocument("file/extended.xml");
		System.out
				.println("----------- print the original object -----------\n"
						+ po);

		System.out.println("----------- modify the object -----------");
		po.getLineItems()
				.add(new LineItem("The Bourne Ultimatum", 5, 20.89, 2));
		po.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2011-08-09 15:20:00"));
		System.out
				.println("----------- print the modified object -----------\n"
						+ po);

		System.out.println("----------- print the generated xml -----------");
		printDocument(writePurchaseOrderToDocument(po), "US-ASCII", System.out);

		System.out
				.println("----------- modify the object's charset -----------");
		po.getLineItems().get(0).setDescription("谍影重重1");
		po.getLineItems().get(1).setDescription("谍影重重2");
		po.getLineItems().get(2).setDescription("谍影重重3");
		System.out
				.println("----------- print the modified object -----------\n"
						+ po);
		System.out.println("----------- print the generated xml -----------");
		printDocument(writePurchaseOrderToDocument(po), "utf-8", System.out);
	}
}
