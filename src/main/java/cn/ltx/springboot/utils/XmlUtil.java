package cn.ltx.springboot.utils;

import org.apache.commons.digester.Digester;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * XML文件数据读取实现类
 *
 * @author ZHENG YI
 */
public class XmlUtil {

    /**
     * Document元素
     */
    Document doc;

    /**
     * 根元素
     */
    Element root;

    /**
     * XML文件路径
     */
    String path;

    /**
     * 验证例外
     */
    Element validateErrors = null;

    /**
     * 构造器
     *
     * @param content 构造内容
     * @param isFile  改内容是否来自于文件
     * @throws DocumentException
     */
    XmlUtil(String content, boolean isFile) throws DocumentException {
        if (isFile) {
            SAXReader reader = new SAXReader();
            try {
                this.doc = reader.read(FileUtil.getFileInputStream(content));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new DocumentException("取得文件 : " + content + " InputStream例外 例外信息 : " + e.getMessage(), e);
            }
            path = content;
        } else {
            doc = DocumentHelper.parseText(content);
            path = null;
        }
        root = doc.getRootElement();
    }


    /**
     * 构造器, 用一个指定的Document来构造该类实例
     *
     * @param document Document
     */
    XmlUtil(Document document) {
        doc = document;
        root = doc.getRootElement();
        path = null;
    }

    /**
     * 使用指定的根节点名称构造一个默认的Document
     *
     * @param rootName 根元素名称
     */
    XmlUtil(String rootName) {
        doc = DocumentHelper.createDocument();
        root = doc.addElement(rootName);
        path = null;
    }

    /**
     * 从根元素下取得符合指定名称的元素节点
     *
     * @param name 节点名称
     * @return Iterator
     */
    public Iterator elementIterator(String name) {
        Iterator nodes = null;
        if (name != null && !"".equals(name))
            nodes = root.elementIterator(name);
        else
            nodes = root.elementIterator();
        return nodes;
    }

    /**
     * 取得Document元素
     *
     * @return Document
     */
    public Document getDocument() {
        return doc;
    }

    /**
     * 获取根元素
     *
     * @return Element
     */
    public Element getRootElemenet() {
        return root;
    }

    /**
     * 使用XPATH语句从XML中取得内容
     *
     * @param xPath 语句
     * @return List
     */
    public Iterator selectNodes(String xPath) {
        return doc.selectNodes(xPath).iterator();
    }

    /**
     * 从指定的元素下取得符合指定名称的元素节点
     *
     * @param node 指定的元素节点
     * @param name 节点名称
     * @return Iterator
     */
    public Iterator elementIterator(Node node, String name) {
        Iterator nodes = null;
        nodes = ((Element) node).elementIterator(name);
        return nodes;
    }

    /**
     * 在根元素下增加一个指定名称的节点
     *
     * @param name 节点名称
     * @return Element <增加的节点>
     */
    public Element addRootElement(String name) {
        return root.addElement(name);
    }

    /**
     * 在指定的Element元素下增加一个Element元素
     *
     * @param element 指定的Element元素
     * @param name    增加的Element元素名称
     * @return Element <增加的节点>
     */
    public Element addElement(Element element, String name) {
        return element.addElement(name);
    }

    /**
     * 在指定的Element元素下增加一个文本Element元素节点
     *
     * @param element 指定的Element元素
     * @param name    增加的Element元素名称
     * @param text    增加的Element元素文件值
     * @return
     */
    public Element addElement(Element element, String name, String text) {
        Element e = element.addElement(name);
        e.setText(text);
        return e;
    }

    /**
     * 在指定的XPATH语句查询出的元素下增加一个Element元素
     *
     * @param xpath 指定的XPATH语句
     * @param name  增加的Element元素名称
     * @return Element <增加的节点>
     */
    public Iterator addElement(String xpath, String name) {
        Iterator nodes = null;
        Node node = null;
        List rtnNodes = new ArrayList();
        nodes = selectNodes(xpath);
        while (nodes.hasNext()) {
            node = (Node) nodes.next();
            rtnNodes.add(((Element) node).addElement(name));
        }
        return rtnNodes.iterator();
    }

    /**
     * 在指定的XPATH语句查询出的元素下增加一个文本Element元素节点
     *
     * @param xpath 指定的XPATH语句
     * @param name  增加的Element元素名称
     * @param value 节点的Text文本值
     * @return Element <增加的节点>
     */
    public Iterator addElement(String xpath, String name, String value) {
        Iterator nodes = addElement(xpath, name);
        while (nodes.hasNext()) {
            ((Element) nodes.next()).setText(value);
        }
        return nodes;

    }

    /**
     * 在指定的XPATH语句查询出的元素下增加一个属性
     *
     * @param xpath 指定的XPATH语句
     * @param name  增加的Element属性名称
     * @param value 属性值
     * @return Element <增加的节点>
     */
    public void addAttribute(String xpath, String name, String value) {
        Iterator nodes = null;
        nodes = selectNodes(xpath);
        while (nodes.hasNext()) {
            ((Element) nodes.next()).addAttribute(name, value);
        }
    }

    /**
     * 将指定的Element原素设置对于的文本值
     *
     * @param element Element原素
     * @param text    文本值
     */
    public void setNodeText(Element element, String text) {
        element.setText(text);
    }

    /**
     * 将指定的Element原素设置对于的属性
     *
     * @param element   Element原素
     * @param attrName  属性名称
     * @param attrValue 属性值
     */
    public void addAttribute(Element element, String attrName, String attrValue) {
        element.addAttribute(attrName, attrValue);
    }

    /**
     * 修改Element元素对应的属性的属性值
     *
     * @param element   Element原素
     * @param attrName  属性名称
     * @param attrValue 属性值
     */
    public void setAttribute(Element element, String attrName, String attrValue) {
        element.attribute(attrName).setValue(attrValue);
    }


    /**
     * 取得XML字符串
     *
     * @return String
     */
    public String asXML() {
        return doc.asXML();
    }

    /**
     * 取得文件保存的路径
     *
     * @return String
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置文件保存的路径
     *
     * @param path 文件保存的路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 将XML内容以文件形式保存
     *
     * @param encoding 编码
     * @throws TipException 操作例外
     */
    public void save(String encoding) throws Exception {
        if (path == null)
            throw new Exception("空的文件存储路径。例外发生DomControl接口中save方法");
        FileUtil.mkDir(path);
        try {
            FileWriter writer = new FileWriter(new File(path));
            write(writer, doc, encoding);
        } catch (IOException e) {
            throw new Exception("XML文件保存例外。例外发生DomControl接口中save方法", e);
        }
    }

    /**
     * 将XML内容以文件形式保存
     *
     * @throws TipException 操作例外
     */
    public void save() throws Exception {
        save(null);
    }

    /**
     * 将XML内容写入指定的输出流中
     *
     * @param out      OutputStream
     * @param encoding 字符编码级
     * @throws TipException 操作例外
     */
    public void write(OutputStream out, String encoding) throws Exception {
        Writer writer = null;
        try {
            if (encoding == null)
                writer = new OutputStreamWriter(out);
            else
                writer = new OutputStreamWriter(out, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        write(writer, doc, encoding);
    }

    /**
     * 将XML内容写入指定的输出流中
     *
     * @param out      OutputStream
     * @param encoding 字符编码级
     * @throws TipException 操作例外
     */
    public void write(OutputStream out) throws Exception {
        write(out, null);
    }

    /**
     * 写入文件
     *
     * @param writer   写入流
     * @param document Document
     * @param encoding 编码
     * @throws TipException 写入文件例外
     */
    private void write(Writer writer, Document document, String encoding) throws Exception {
        XMLWriter xmlWriter = null;
        OutputFormat format = OutputFormat.createPrettyPrint();
        if (encoding != null && !"".equals(encoding.trim()))
            format.setEncoding(encoding);
        try {
            xmlWriter = new XMLWriter(writer, format);
            xmlWriter.write(document);

        } catch (IOException e) {
            throw new Exception("XML文件写入失败", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new Exception("关闭XML文件写入流失败", e);
                }
            }
        }
    }

    /**
     * 清除根元素下的所有元素
     */
    public void clearRootContent() {
        root.clearContent();
    }

    /**
     * XSD验证XML文件
     *
     * @param xsdFilePath XSD文件的路径
     * @return
     * @throws Exception boolean
     * @throws
     * @since 1.0.0
     */
    public boolean validateXSD(String xsdFilePath) throws Exception {
        boolean validate = true;
        try {
            //创建默认的XML错误处理器
            XMLErrorHandler errorHandler = new XMLErrorHandler();
            //获取基于 SAX 的解析器的实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //解析器在解析时验证 XML 内容。
            factory.setValidating(true);
            //指定由此代码生成的解析器将提供对 XML 名称空间的支持。
            factory.setNamespaceAware(true);
            //使用当前配置的工厂参数创建 SAXParser 的一个新实例。
            SAXParser parser = factory.newSAXParser();

            //设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在 [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource",
                    "file:" + xsdFilePath);
            //创建一个SAXValidator校验工具，并设置校验工具的属性
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            //设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
            validator.setErrorHandler(errorHandler);
            //校验
            validator.validate(this.doc);
            //如果错误信息不为空，说明校验失败
            if (errorHandler.getErrors().hasContent()) {
                this.validateErrors = errorHandler.getErrors();
                validate = false;
            }
        } catch (Exception ex) {
            System.out.println("XML文件: " + this.path + " 通过XSD文件:"
                    + xsdFilePath + "检验失败。\n原因： " + ex.getMessage());
            ex.printStackTrace();
        }
        return validate;
    }


    private static Document document = null;

    public static File file = null;

    /**
     * @param filePath 文件路径
     * @throws
     * @author zhangmin fildElementByiD
     * 载入一个xml文档
     * @since 1.0.0
     */

    public static void LoadXML(String filePath) throws Exception {
        SAXReader saxReader = new SAXReader();
        file = new File(filePath);
        if (file.exists()) {
            try {
                document = saxReader.read(file);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    public static Document getDoc() {
        return document;
    }

    public static File getFile() {
        return file;
    }


    /**
     * @param id 标识的值
     * @return Element  该标识对应的节点
     * @throws
     * @author zhangmin fildElementByiD
     * 通过标识查找xml中相关的节点信息
     * @since 1.0.0
     */

    public static Element findElement(String id) {
        Element ele = null;
        if (document != null) {
            ele = document.elementByID(id);
        } else {
            System.out.println("没有找到相应节点信息");
        }
        return ele;
    }

    /**
     * @param XPath 节点路径
     * @return List
     * @throws
     * @author zhangmin
     * findElements  通过xpath查找原色节点
     * @since 1.0.0
     */
    public static List findElements(String XPath) {
        List ele = null;
        if (document != null) {
            ele = document.selectNodes(XPath);
        } else {
            System.out.println("没有找到相应节点信息");
        }
        return ele;
    }


    /**
     * @param XPath     节点路径
     * @param attrName  属性名称
     * @param attrValue 属性值
     * @return List    符合条件的element
     * @throws
     * @author zhangmin
     * findElement  查找XPath路径下,属性attrName的值为属性是节点集合
     * @since 1.0.0
     */
    public static List findElement(String XPath, String attrName, String attrValue) {
        List element = new ArrayList();
        if (document != null) {
            List list = document.selectNodes(XPath);

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                String s1 = node.attributeValue(attrName);
                if (s1 != null && s1.equals(attrValue)) {
                    element.add(node);
                }

            }
        }
        // TODO: implement
        return element;
    }

    /**
     * @param id   要查找的节点的id值
     * @param attr 要查找的节点的属性名称
     * @return String  相应的节点下的属性的值
     * @throws
     * @author zhangmin
     * findAttr  查找某标识节点的相关属性值
     * @since 1.0.0
     */

    public static String findAttrbuteValue(String id, String attr) {
        // TODO: implement
        Element ele = findElement(id);
        String str = ele.attributeValue(attr);
        return str;
    }

    /**
     * @param XPath     节点路径
     * @param attrName  属性名称
     * @param attrValue 属性值
     * @return List    符合条件的element
     * @throws
     * @author zhangmin
     * findChildEle  查找XPath路径下,父节点属性attrName的值为属性是节点集合
     * @since 1.0.0
     */
    public static List findChildren(String XPath, String attrName, String attrValue) {
        List element = new ArrayList();
        if (document != null) {
            List list = document.selectNodes(XPath);

            for (int i = 0; i < list.size(); i++) {
                Node node = (Node) list.get(i);
                String s1 = node.getParent().attributeValue(attrName);
                if (s1 != null && s1.equals(attrValue)) {
                    element.add(node);
                }
            }
        }

        // TODO: implement
        return element;
    }

    /**
     * @param id 对应的id
     * @return String  返回相应的字符串
     * @throws
     * @author zhangmin
     * findText  查找某一节点的文本信息
     * @since 1.0.0
     */
    public static String findText(String id) {
        // TODO: implement
        return findElement(id).getText();
    }

    /**
     * @param list        元素为element的集合
     * @param childerAttr element节点的属性名称
     * @return String
     * @throws
     * @author zhangmin
     * findChilderAttribute  查找一个节点集合的attrName的属性属性值
     * @since 1.0.0
     */

    public static List findAttributeValue(List list, String attr) {
        // TODO: implement
        List attrValueList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Element el = (Element) list.get(i);
            attrValueList.add(el.attributeValue(attr));
        }
        return attrValueList;
    }

    /**
     * @param element 节点元素的集合
     * @param attr    属性名称
     * @return String
     * @throws
     * @author zhangmin
     * findAttributeValue  查找一个节点的attrName属性对应的值
     * @since 1.0.0
     */
    public static String findAttributeValue(Element element, String attr) {
        // TODO: implement
        String attrValue = null;
        if (element != null) {
            attrValue = element.attributeValue(attr);
        }
        return attrValue;
    }

    /**
     * @param element 节点元素
     * @return String
     * @throws
     * @author zhangmin
     * findChilderText  查找节点元素的文本信息
     * @since 1.0.0
     */
    public static String findElementText(Element element) {
        // TODO: implement
        String text = null;

        if (element != null) {
            text = element.getText();
        }
        return text;
    }

    /**
     * @return Digester   Digester对象
     * @throws
     * @author zhangmin
     * getDigester  封装tomcat的解析xml文件的方法，用于xml文件与javabean的映射
     * @since 1.0.0
     */
    public static Digester getDigester() {
        // TODO: implement
        Digester digester = new Digester();
        return digester;
    }

}
