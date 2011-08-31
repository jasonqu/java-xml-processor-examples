package example.xstream;

import java.text.SimpleDateFormat;

import javax.xml.parsers.ParserConfigurationException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.basic.StringConverter;

import example.model.extended.Customer;
import example.model.extended.LineItem;
import example.model.extended.PurchaseOrder;
import example.model.extended.Shipper;

/**
 * show the example of step 5
 */
public class XStreamExampleReadingAndWritingModified {
	private static XStream xstreamIn;
	private static XStream xstreamOut;
	static {
		xstreamIn = new XStream();
		xstreamIn.alias("purchase-order", PurchaseOrder.class);
		xstreamIn.alias("customer", Customer.class);
		xstreamIn.alias("line-item", LineItem.class);
		xstreamIn.registerConverter(new DateConverter("yyyy-MM-dd hh:mm:ss", new String[]{""}));
		xstreamIn.addImplicitCollection(PurchaseOrder.class, "lineItems");
		xstreamIn.aliasField("per-unit-ounces", LineItem.class, "perUnitOunces");
		xstreamIn.aliasField("per-ounce-rate", Shipper.class, "perOunceRate");
		xstreamIn.useAttributeFor(Customer.class, "email");
		xstreamIn.registerConverter(new StringConverter());
		
		xstreamOut = new XStream();
		xstreamOut.alias("purchase-order", PurchaseOrder.class);
		xstreamOut.alias("customer", Customer.class);
		xstreamOut.alias("lineItem", LineItem.class);
		xstreamOut.registerConverter(new DateConverter("yyyy-MM-dd hh:mm:ss", new String[]{""}));
		xstreamOut.addImplicitCollection(PurchaseOrder.class, "lineItems");
		xstreamOut.aliasField("city", Customer.class, "address");
		xstreamOut.aliasField("ouncesPerUnit", LineItem.class, "perUnitOunces");
		xstreamOut.aliasField("ratePerOunce", Shipper.class, "perOunceRate");
		xstreamOut.useAttributeFor(Customer.class, "email");
		xstreamOut.registerConverter(new StringConverter());
	}

	/**
	 * parse the xml document into PurchaseOrder object 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private static PurchaseOrder getPurchaseOrderFromDocument(String file)
			throws Exception {
		return (PurchaseOrder) XmlProcessor.readObjectFromMetaFile(xstreamIn, file);
	}

	/**
	 * parse the PurchaseOrder object into an xml document 
	 * @param po
	 * @return
	 * @throws ParserConfigurationException
	 */
	private static String writePurchaseOrderToString(PurchaseOrder po)
			throws ParserConfigurationException {
		return XmlProcessor.writeObjectToString(xstreamOut, po);
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("----------- get the object from xml file -----------");
		PurchaseOrder po = getPurchaseOrderFromDocument("file/extended.xml");
		System.out.println("----------- print the original object -----------\n" + po);

		System.out.println("----------- modify the object -----------");
		po.getLineItems().add(new LineItem("The Bourne Ultimatum", 5, 20.89, 2));
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
