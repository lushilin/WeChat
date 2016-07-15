package com.tcps.pay.common;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XMLHandler {
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对那些xml节点的转换增加CDATA标记 true增加 false反之
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				@Override
				public String encodeNode(String name) {
					return name;
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	// 将获得的xml字符串转换成map处理
	public static Map<String, String> XMLStrToMap(String respString) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(respString);
			Element root = document.getRootElement();
			for (Iterator it = root.elementIterator(); it.hasNext();) {
				Element element = (Element) it.next();
				map.put(element.getName(), element.getText());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	// 将对象转换为xml字符串
	public static String objectToXMLStr(Object object) {
		try {
			xstream.alias("xml", object.getClass());
			String result = xstream.toXML(object);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static Object simpleXMLStrToObj(String xmlStr, Class oClass) {
		XStream xStream = new XStream(new Dom4JDriver());
		xStream.alias("xml", oClass);
		xStream.ignoreUnknownElements();
		return xStream.fromXML(xmlStr);
	}

	public static String mapToXML(Map<String,String> map) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");
		for(Map.Entry<String, String> entry : map.entrySet()){
			root.addElement(entry.getKey()).setText(entry.getValue());
		}
		return document.asXML();
	}

}
