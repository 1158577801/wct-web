package com.cn.wct.data;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * xml工具
 * @author  朱国俊
 * @version 2013-3-21
 * @since   JDK1.6
 */
public final class XmlUtil {

    /**
     * 构造函数
     */
    private XmlUtil() { }

    /**
     * 创建Document并返回根结点
     * @param rootLable 根标签
     * @return dom4j的Document对象
     */
    public static Document createDocument(String rootLable) {
        Document doc = DocumentHelper.createDocument();
        doc.setRootElement(doc.addElement(rootLable));
        return doc;
    }

    /**
     * 将字符串转化为XML
     * @param xmlString 文件内容
     * @return dom4j的Document对象
     */
    public static Document createDocumentFromXmlString(String xmlString) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 给结点加子结点
     * @param root 相对根
     * @param newNode 新结点
     * @param nodeId 结点Id
     * @param elements 结点元素
     */
    public static void addNodeElement(Element root, String newNode, String nodeId, Map elements) {
        Element newNode2 = root.addElement(newNode);
        newNode2.addAttribute("id", nodeId).addAttribute("no", nodeId); // 设置属性
        Iterator it = elements.entrySet().iterator();
        while (it.hasNext()) { // 结点下的元素与值
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            newNode2.addElement((String) key).setText((String) value);
        }
    }


    /**
     * 打印节点的所有子节点
     * @param element 节点element
     */
    public static void printAllChildNode(Element element) {
        // 循环当前节点属性
        Iterator attrs = element.attributeIterator();
        while (attrs.hasNext()) {
            Attribute attr = (Attribute) attrs.next();
        }

        // 循环其孩子元素节点
        Iterator elements = element.elementIterator();
        while (elements.hasNext()) {
            Element ele = (Element) elements.next();

            // 递归调用
            printAllChildNode(ele);
        }
    }

    /***
     * 以xpath读取数据
     * @param document dom4j的document对象
     * @param xpath xpath路径
     * @return Element
     */
    public static Element findNodeByXPath(Document document, String xpath) {
        List<Element> nodes = document.selectNodes(xpath);
        if (nodes.size() > 0) {
            return nodes.get(0);
        } else {
            return null;
        }

    }

    /***
     * 以xpath读取数据
     * @param document doc dom4j的Document对象
     * @param xpath xpath路径
     * @return Element列表
     */
    public static List<Element> findNodesByXPath(Document document, String xpath) {
        List<Element> nodes = document.selectNodes(xpath);
        return nodes;
    }

    /**
     * 读取XML文件
     * @param file 文件
     * @return Document对象
     * @throws Exception 任何异常
     */
    public static Document readXml(File file) {
    	try {
    		SAXReader reader = new SAXReader();
            Document doc = reader.read(file);
            return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 读取XML文件
     * @param file 文件
     * @return Document对象
     * @throws Exception 任何异常
     */
    public static Document readXml(InputStream is) {
    	try {
    		SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * 保存文件
     * @param file 文件
     * @param document 文件
     */
    public static void saveFile(File file, Document document) {
        try {
            /* 将document中的内容写入文件中 */
            // 默认为UTF-8格式，指定为"GB2312"
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("GB2312");
            XMLWriter writer = new XMLWriter(new FileWriter(file), format);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * main函数
//     * @param args 参数
//     */
//    public static void main(String[] args) {
//        String filePath = "D:/excel_question.xml";
//        Document document;
//        try {
//            document = XMLUtils.readXml(filePath);
//
//            List<Node> nodes = document.selectNodes("//cell[@name='patientId']");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
