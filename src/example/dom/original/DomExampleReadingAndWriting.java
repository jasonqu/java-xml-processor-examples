package example.dom.original;

import static example.dom.original.Util.createTextTag;
import static example.dom.original.Util.getNodeValue;
import static example.dom.original.Util.printDocument;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import example.model.original.Customer;
import example.model.original.LineItem;
import example.model.original.PurchaseOrder;

/**
 * show the example of step 1, 2 and 3
 */
public class DomExampleReadingAndWriting {

	/**
	 * parse the xml document into PurchaseOrder object 
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
		po.setCustomer(c);

		String date = getNodeValue("date", root);
		po.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));

		NodeList list = doc.getElementsByTagName("line-item");
		for (int i = 0; i < list.getLength(); i++) {
			Element node = (Element) list.item(i);
			LineItem li = new LineItem();
			li.setDescription(getNodeValue("description", node));
			li.setPrice(Double.parseDouble(getNodeValue("price", node)));
			li.setPrice(Integer.parseInt(getNodeValue("quantity", node)));
			po.getLineItems().add(li);
		}
		return po;
	}

	/**
	 * parse the PurchaseOrder object into an xml document 
	 * @param po
	 * @return
	 * @throws ParserConfigurationException
	 */
	private static Document writePurchaseOrderToDocument(PurchaseOrder po)
			throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element rootElement = doc.createElement("purchase-order");
		doc.appendChild(rootElement);

		Element customer = doc.createElement("customer");
		rootElement.appendChild(customer);

		createTextTag(doc, customer, "name", po.getCustomer().getName());
		createTextTag(doc, customer, "address", po.getCustomer().getAddress());

		createTextTag(doc, rootElement, "date", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(po.getDate()));

		for (LineItem li : po.getLineItems()) {
			Element lineItem = doc.createElement("line-item");
			createTextTag(doc, lineItem, "description", li.getDescription());
			createTextTag(doc, lineItem, "price",
					Double.toString(li.getPrice()));
			createTextTag(doc, lineItem, "quantity",
					Integer.toString(li.getQuantity()));
			rootElement.appendChild(lineItem);
		}

		return doc;
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("----------- get the object from xml file -----------");
		PurchaseOrder po = getPurchaseOrderFromDocument("file/original.xml");
		System.out.println("----------- print the original object -----------\n" + po);

		System.out.println("----------- modify the object -----------");
		po.getLineItems().add(new LineItem("The Bourne Ultimatum", 20.89, 2));
		po.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse("2011-08-09 15:20:00"));
		System.out.println("----------- print the modified object -----------\n" + po);

		System.out.println("----------- print the generated xml -----------");
		printDocument(writePurchaseOrderToDocument(po), "US-ASCII", System.out);
		
		System.out.println("----------- modify the object's charset -----------");
		po.getLineItems().get(0).setDescription("谍影重重1");
		po.getLineItems().get(1).setDescription("谍影重重2");
		po.getLineItems().get(2).setDescription("谍影重重3");
		System.out.println("----------- print the modified object -----------\n" + po);
		System.out.println("----------- print the generated xml -----------");
		printDocument(writePurchaseOrderToDocument(po), "utf-8", System.out);
	}
}
