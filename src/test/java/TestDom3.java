import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestDom3 {
    private static List<StringBuffer> sql1 = new ArrayList<>();
    private static List<StringBuffer> sql2 = new ArrayList<>();

    /**
     * 从指定节点开始,递归遍历所有子节点
     *
     * @author chenleixing
     */
    public static void getNodes(Element node) {
        List<Attribute> listAttr = node.attributes();//当前节点的所有属性的list
        for (Attribute attr : listAttr) {//遍历当前节点的所有属性
            if ("resourceId".equalsIgnoreCase(attr.getName())) {
                Element element = attr.getParent();
                List<Attribute> attributes = element.attributes();
                StringBuffer sb = new StringBuffer("'");
                for (int i = 0; i < attributes.size(); i++) {
                    if (attributes.get(i).getName().equalsIgnoreCase("resourceId")) {
                        sb.append(attributes.get(i).getValue()).append("','");
                    }
                }
                sb.delete(sb.lastIndexOf(","), sb.length());
                sql1.add(sb);
            }
        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();//所有一级子节点的list
        for (Element e : listElement) {//遍历所有一级子节点
            getNodes(e);//递归
        }
    }

    public static void main(String[] args) throws Exception {
        SAXReader sax = new SAXReader();//创建一个SAXReader对象
        File xmlFile = new File("D:\\Projects\\TopICIS_V1.45\\litianxiang_main3_TopICIS_V1.45\\TopICIS33_6\\dist\\res\\workflow_tab\\workflow-law-case.xml");//根据指定的路径创建file对象
        Document document = sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root = document.getRootElement();//获取根节点
        getNodes(root);//从根节点开始遍历所有节点
        printList(sql1);
        printList(sql2);
    }

    private static void printList(List list) {
        System.out.println("==========================================start");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
        System.out.println("==========================================end");
    }
}
