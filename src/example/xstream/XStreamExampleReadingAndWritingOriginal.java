package example.xstream;

import java.text.SimpleDateFormat;

import javax.xml.parsers.ParserConfigurationException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

import example.model.original.Customer;
import example.model.original.LineItem;
import example.model.original.PurchaseOrder;

/**
 * show the example of step 1, 2 and 3
 */
public class XStreamExampleReadingAndWritingOriginal {
	private static XStream xstream;
	static {
		xstream = new XStream();
		xstream.alias("purchase-order", PurchaseOrder.class);
		xstream.alias("customer", Customer.class);
		xstream.alias("line-item", LineItem.class);
		xstream.registerConverter(new DateConverter("yyyy-MM-dd hh:mm:ss", new String[]{""}));
		xstream.addImplicitCollection(PurchaseOrder.class, "lineItems");
	}

	/**
	 * parse the xml document into PurchaseOrder object 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private static PurchaseOrder getPurchaseOrderFromDocument(String file)
			throws Exception {
		return (PurchaseOrder) XmlProcessor.readObjectFromMetaFile(xstream, file);
	}

	/**
	 * parse the PurchaseOrder object into an xml document 
	 * @param po
	 * @return
	 * @throws ParserConfigurationException
	 */
	private static String writePurchaseOrderToString(PurchaseOrder po)
			throws ParserConfigurationException {
		return XmlProcessor.writeObjectToString(xstream, po);
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
		System.out.println(writePurchaseOrderToString(po));
		
		System.out.println("----------- modify the object's charset -----------");
		po.getLineItems().get(0).setDescription("谍影重重1");
		po.getLineItems().get(1).setDescription("谍影重重2");
		po.getLineItems().get(2).setDescription("谍影重重3");
		System.out.println("----------- print the modified object -----------\n" + po);
		System.out.println("----------- print the generated xml -----------");
		System.out.println(writePurchaseOrderToString(po));
	}
}
