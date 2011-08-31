package example.xstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

/**
 *  对象与xml文件转换方法
 */
public class XmlProcessor {
	public static Object readObjectFromMetaFile(XStream xstream, String filename) throws IOException {
		FileInputStream fi = new FileInputStream(filename);
		try {
			return xstream.fromXML(fi);
		} finally {
			fi.close();
		}
	}

	public static Object readObjectFromMetaFile(XStream xstream, File file) throws IOException {
		FileInputStream fi = new FileInputStream(file);	
		try {
			return xstream.fromXML(fi);
		} finally {
			fi.close();
		}
	}

	public static void writeObjectToMetaFile(XStream xstream, Object obj, String filename) throws IOException{
		FileOutputStream os = new FileOutputStream(filename);
		xstream.toXML(obj, os);
		os.close();
	}

	public static void writeObjectToStream(XStream xstream, Object obj, OutputStream out){
		xstream.toXML(obj, out);
	}

	public static Object readObjectFromString(XStream xstream, String stringIn){
		return xstream.fromXML(stringIn);
	}

	public static String writeObjectToString(XStream xstream, Object obj){	
		return xstream.toXML(obj);
	}
}
