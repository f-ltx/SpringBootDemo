package cn.ltx.springboot.utils;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 记录日志的类 主要输出字符串
 *
 * @author Administrator
 */

public class Log4jUtil {

    private static Log4jUtil log4jUtil;

    private static Logger logger = (Logger) LoggerFactory.getLogger(Log4jUtil.class);


    public Log4jUtil(String configPath) {

        PropertyConfigurator.configure(configPath);

        // this.logger = Logger.getRootLogger();

        Log4jUtil.logger = Logger.getLogger(Log4jUtil.class);

    }


    public static Log4jUtil getLog4jUtil() {

        if (log4jUtil == null) {

            log4jUtil = new Log4jUtil("config/log4j.properties");

        }

        return log4jUtil;

    }


    public static void debug(String str) {

        Log4jUtil.logger.debug(str);

    }


    public static void info(String str) {

        Log4jUtil.logger.info(str);

    }


    public static void warn(String str) {

        Log4jUtil.logger.warn(str);

    }


    public static void error(String str) {

        Log4jUtil.logger.error(str);

    }


    public static void fatal(String str) {

        Log4jUtil.logger.fatal(str);

    }


    public static void main(String[] args) {

        // 测试字符串

        // testString();


        // 测试数组

        // Object[] array = new Object[] { "测试array", "测试array1", "测试array2" };

        // testArray(array);


        // 测试List集合

        // //List放的是字符串

        // List<String> list = new ArrayList<String>();

        // list.add("测试list1");

        // list.add("测试list2");

        // list.add("测试list3");

        // //List放的是对象。

        // List<Student> list = new ArrayList<Student>();

        // for (int i = 0; i < 3; i++) {

        // Student s = new Student();

        // s.setId(i);

        // s.setName("name"+i);

        // list.add(s);

        // }

        // testList(list);


        //测试map集合

        Map<String, Object> map = new HashMap<String, Object>();


        testMap(map);

    }


    public static void testString() {

        Log4jUtil log4jUtil = Log4jUtil.getLog4jUtil();

        String debug = "debug信息";

        String warn = "warn信息";

        String info = "info信息";

        String error = "error信息";

        String fatal = "fatal信息";

        Log4jUtil.debug(debug);

        Log4jUtil.warn(warn);

        Log4jUtil.info(info);

        Log4jUtil.error(error);

        Log4jUtil.fatal(fatal);

    }


    public static void testArray(Object[] array) {

        Log4jUtil log4jUtil = Log4jUtil.getLog4jUtil();

        int length = array.length;

        for (int i = 0; i < length; i++) {

            // System.out.println("第"+i+"号元素的值："+Array.get(array, i));

            Log4jUtil.debug("第" + i + "号元素的值：" + Array.get(array, i));

        }

    }


    public static <T> void testList(List<T> list) {

        Log4jUtil log4jUtil = Log4jUtil.getLog4jUtil();

        if (list == null) {

            Log4jUtil.error("list=null");

        } else if (list.size() == 0) {

            Log4jUtil.info("list.size()为0");

        } else {

            StringBuffer sb = new StringBuffer();

            String s = new String("list中数据个数：" + list.size());

            sb.append("\r\n" + s + "\r\nstart:----------------------\r\n");

            for (T t : list) {

                sb.append(t.toString() + "\r\n");// 这里List包含的类要有自定义的toString方法

            }

            sb.append("end----------------------\r\n");

            Log4jUtil.debug(sb.toString());

        }

    }


    public static <V, K> void testMap(Map<K, V> map) {

        // Map<Student, Student> mapTemp = new HashMap<Student, Student>();

        Log4jUtil log4jUtil = Log4jUtil.getLog4jUtil();

        if (map == null) {

            Log4jUtil.error("map=null");

        } else if (map.size() == 0) {

            Log4jUtil.info("map.size()为0");

        } else {

            StringBuffer sb = new StringBuffer();

            String s = new String("map中数据个数：" + map.size());

            sb.append("\r\n" + s + "\r\nstart:----------------------\r\n");

            for (K k : map.keySet()) {

                sb.append("key[" + k.toString() + "]所对应的value:["

                        + map.get(k).toString() + "]\r\n");// 这里List包含的类要有自定义的toString方法

            }

            sb.append("end----------------------\r\n");

            Log4jUtil.debug(sb.toString());

        }

    }


    public static void test2() {

        PropertyConfigurator.configure("config/log4j.properties");

        // Logger logger = Logger.getLogger(Log4jTest1.class);

        Logger logger = Logger.getRootLogger();

        // for (int i = 0; i < 1000; i++) {

        logger.debug("debug");

        logger.info("info");

        logger.error("error");

        // }

    }

}