import cn.ltx.springboot.utils.UuidUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestDom {
    private static List<StringBuffer> sql1 = new ArrayList<>();
    private static List<StringBuffer> sql2 = new ArrayList<>();

    /**
     * 从指定节点开始,递归遍历所有子节点
     *
     * @author chenleixing
     */
    public static void getNodes(Element node) {
//        System.out.println("--------------------");

//        //当前节点的名称、文本内容和属性
//        System.out.println("当前节点名称："+node.getName());//当前节点名称
//        System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
        List<Attribute> listAttr = node.attributes();//当前节点的所有属性的list
        for (Attribute attr : listAttr) {//遍历当前节点的所有属性
//            String name = attr.getName();//属性名称
//            String value = attr.getValue();//属性的值
//            System.out.println("属性名称：" + name + "属性值：" + value);
            if ("url".equalsIgnoreCase(attr.getName())) {
                Element element = attr.getParent();
                String resourceId = UuidUtil.getUuid();
                StringBuffer resourceSql = new StringBuffer("INSERT INTO aaf_resource ");
                resourceSql.append("(id, create_time, operate_user_id, state, update_time, code, description, NAME, res_source, res_type, uri) VALUES ('");
                resourceSql.append(resourceId);
                resourceSql.append("', SYSDATE, '1', '01', SYSDATE, '");
                String code = "";
                String description = "";
                String name = "";
                String uri = "";
                List<Attribute> attributes = element.attributes();
                for (int i = 0; i < attributes.size(); i++) {
                    if (attributes.get(i).getName().equalsIgnoreCase("resourceId")) {
                        code = attributes.get(i).getValue();
                    }
                    if (attributes.get(i).getName().equalsIgnoreCase("descript")) {
                        description = attributes.get(i).getValue();
                    }
                    if (attributes.get(i).getName().equalsIgnoreCase("name")) {
                        name = attributes.get(i).getValue();
                    }
                    if (attributes.get(i).getName().equalsIgnoreCase("url")) {
                        uri = attributes.get(i).getValue().substring(0, attributes.get(i).getValue().indexOf(".do")+3);
                    }
                }
                resourceSql.append(code).append("', '");
                resourceSql.append(description).append("', '");
                resourceSql.append(name).append("', 'OGS01', '02', '");
                resourceSql.append(uri).append("*');");
                boolean flag = true;
                //去重
                for (StringBuffer stringBuffer : sql1) {
                    if(stringBuffer.indexOf(uri) != -1){
                        flag = false;
                    }
                }
                if(flag){
                    sql1.add(resourceSql);
                    String rrId = UuidUtil.getUuid();
                    StringBuffer rrSql = new StringBuffer("INSERT INTO aaf_role_resource (id, create_time, operate_user_id, res_id, role_id) VALUES ('");
                    rrSql.append(rrId);
                    rrSql.append("', SYSDATE, '999', '");
                    rrSql.append(resourceId);
                    rrSql.append("', '402890b66acdbdbe016acdd731a40002');");
                    sql2.add(rrSql);
                }
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

    private static void printList(List list){
        System.out.println("==========================================start");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("==========================================end");
    }
}
