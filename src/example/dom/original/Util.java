package example.dom.original;

import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Util {
	private Util(){}
	
	/**
	 * Print the xml content of the document
	 * @param doc
	 * @param encoding
	 * @param output
	 * @throws Exception
	 */
	public static void printDocument(Document doc, String encoding, OutputStream output)
			throws Exception {
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		DOMSource source = new DOMSource(doc);
		transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, new StreamResult(output));
	}

	/**
	 * This method is just for the end-point node, such as the customer.address
	 * in original.xml 
	 * 获取端点的文本内容，该方法只适合于端点，如original.xml中的customer.address
	 * 
	 * @param tag
	 * @param element
	 * @return
	 */
	public static String getNodeValue(String tag, Element element) {
		return element.getElementsByTagName(tag).item(0).getChildNodes()
				.item(0).getNodeValue();
	}
	
	/**
	 * Create the document element 
	 * @param doc
	 * @param elementToBeAppend
	 * @param tag
	 * @param text
	 */
	public static void createTextTag(Document doc, Element elementToBeAppend,
			String tag, String text) {
		Element name = doc.createElement(tag);
		name.appendChild(doc.createTextNode(text));
		elementToBeAppend.appendChild(name);
	}

}
