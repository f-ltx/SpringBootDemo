package cn.ltx.springboot.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 字符串 DESede(3DES) 加密 设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的密钥，P代表明文，C代表密表， 3DES加密过程为：C=Ek3(Dk2(Ek1(P)))　
 * 3DES解密过程为：P=Dk1((EK2(Dk3(C))) 对称密钥加密又叫专用密钥加密，即发送和接收数据的双方必使用相同的密钥对明文进行加密和解密运算。
 * 对称密钥加密算法主要包括：DES、3DES、IDEA、FEAL、BLOWFISH等。
 */
public class Utils3DES {
   private static final String Algorithm = "DESede"; // 定义加密算法,可用 DES,DESede,Blowfish

   // keyBytes为加密密钥，长度为24字节
   private static final byte[] keyBytes = { 0x31, 0x22, 0x4F, 0x58, (byte) 0x83, 0x10, 0x21, 0x38, 0x21, 0x25, 0x79,
         0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x21, 0x54, 0x36, (byte) 0xE2 };

   // src为被加密的数据缓冲区（源）
   public static byte[] encryptMode(byte[] src) {
      try {
         SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);// 生成密钥
         Cipher c1 = Cipher.getInstance(Algorithm);// 加密
         c1.init(Cipher.ENCRYPT_MODE, deskey);
         return c1.doFinal(src);// 在单一方面的加密或解密
      } catch (java.lang.Exception e3) {
         e3.printStackTrace();
      }
      return null;
   }

   // src为加密后的缓冲区
   public static byte[] decryptMode(byte[] src) {
      try {
         SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);// 生成密钥
         Cipher c1 = Cipher.getInstance(Algorithm);// 解密
         c1.init(Cipher.DECRYPT_MODE, deskey);
         return c1.doFinal(src);
      } catch (java.lang.Exception e3) {
         e3.printStackTrace();
      }
      return null;
   }

   /**
    * 加密String明文输入,String密文输出
    * 
    * @param strMing
    * @return
    */

   public static String encryptString(String strMing) {
      byte[] byteMi = null;
      byte[] byteMing = null;
      String strMi = "";
      BASE64Encoder base64en = new BASE64Encoder();
      try {
         byteMing = strMing.getBytes("UTF8");
         byteMi = encryptMode(byteMing);
         strMi = base64en.encode(byteMi);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         base64en = null;
         byteMing = null;
         byteMi = null;
      }
      return strMi;
   }

   /**
    * 解密 以String密文输入,String明文输出
    * 
    * @param strMi
    * @return
    */

   public static String decryptString(String strMi) {
      BASE64Decoder base64De = new BASE64Decoder();
      byte[] byteMing = null;
      byte[] byteMi = null;
      String strMing = "";
      try {
         byteMi = base64De.decodeBuffer(strMi);
         byteMing = decryptMode(byteMi);
         strMing = new String(byteMing, "UTF8");
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         base64De = null;
         byteMing = null;
         byteMi = null;
      }
      return strMing;
   }

   public static void main(String[] args) {
      // String szSrc
      // ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Case><INTERFACE_VERIFY name=\"接口调用验证信息\"><INTERFACE_VERIFY_LOGIN_NAME name=\"接口验证登陆名\">测试接口</INTERFACE_VERIFY_LOGIN_NAME><INTERFACE_VERIFY_LOGIN_PASSWORD name=\"接口验证登陆密码\">0</INTERFACE_VERIFY_LOGIN_PASSWORD><INTERFACE_VERIFY_IP name=\"接口验证登陆IP\">192.168.3.16,8.8.8.8,5.3.3.3</INTERFACE_VERIFY_IP></INTERFACE_VERIFY><CASE_XZCF name=\"行政处罚录入基本信息\"><CASE_NAME name=\"案名\">接口插入的移送案名_加密解密</CASE_NAME><WFQKGY name=\"基本案情\">非法出售用于骗取出口退税、抵扣税款发票罪,骗取出口退税罪</WFQKGY><P_INDEX name=\"案发区域代码：《接口定义说明》附录9\">110000</P_INDEX><P_NAME name=\"案发区域名称：《接口定义说明》附录9\">北京市</P_NAME><CASE_OCCUR_DATE name=\"案发时间：格式 yyyy-MM-dd\">2012-11-28</CASE_OCCUR_DATE><WF_MONEY name=\"涉案金额\">100.22</WF_MONEY><CASE_CC_DATE name=\"立案时间：格式 yyyy-MM-dd\">2012-11-30</CASE_CC_DATE><CFXX name=\"处罚信息\">罚款100元人民币</CFXX><XZAJH name=\"行政案号\">皖A100S-XXXX</XZAJH><CF_DATE name=\"处罚时间：格式 yyyy-MM-dd\">2012-11-30</CF_DATE><CF_TYPES name=\"处罚种类：种类ID以逗号分隔\">840</CF_TYPES><CFYJ name=\"处罚依据\">中华人民共和国XXX号文件</CFYJ><CFJDS name=\"处罚决定书\">XXXXXXXXXXXXX</CFJDS><CF_RESULT name=\"处罚结果\">罚款100元人民币</CF_RESULT><DEPT_ID name=\"上报部门ID：《接口定义说明》附录8\">848</DEPT_ID><DEPT_NAME name=\"上报部门名称：《接口定义说明》附录8\">文化部</DEPT_NAME><ORDER_VALUE name=\"排序值\">0</ORDER_VALUE></CASE_XZCF><CASE_PERSONS><CASE_PERSON_SAXX name=\"涉案人员列表\"><NAME name=\"姓名\">接口：张三1</NAME></CASE_PERSON_SAXX><CASE_PERSON_SAXX name=\"涉案人员列表\"><NAME name=\"姓名\">接口：张三2</NAME></CASE_PERSON_SAXX></CASE_PERSONS><INVOLE_CASE_UNITS><INVOLE_CASE_UNIT_SAXX name=\"涉案单位列表\"><UNIT_NAME name=\"单位名称\">test</UNIT_NAME></INVOLE_CASE_UNIT_SAXX><INVOLE_CASE_UNIT_SAXX name=\"涉案单位列表\"><UNIT_NAME name=\"单位名称\">test1</UNIT_NAME></INVOLE_CASE_UNIT_SAXX></INVOLE_CASE_UNITS></Case>";
      // System.out.println("加密前的字符串:" + szSrc);
      // byte[] encoded = encryptMode(szSrc.getBytes());
      // System.out.println("加密后的字符串55:" + (encoded.toString()));
      // System.out.println("加密后的字符串:" + new String(encoded));
      // byte[] srcBytes = decryptMode(encoded.toString().getBytes());
      // System.out.println("解密后的字符串:" + (new String(srcBytes)));

      String strEnc = encryptString("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Case><INTERFACE_VERIFY name=\"接口调用验证信息\"><INTERFACE_VERIFY_LOGIN_NAME name=\"接口验证登陆名\">测试接口</INTERFACE_VERIFY_LOGIN_NAME><INTERFACE_VERIFY_LOGIN_PASSWORD name=\"接口验证登陆密码\">0</INTERFACE_VERIFY_LOGIN_PASSWORD><INTERFACE_VERIFY_IP name=\"接口验证登陆IP\">192.168.3.16,8.8.8.8,5.3.3.3</INTERFACE_VERIFY_IP></INTERFACE_VERIFY><CASE_XZCF name=\"行政处罚录入基本信息\"><CASE_NAME name=\"案名\">接口插入的移送案名_加密解密</CASE_NAME><WFQKGY name=\"基本案情\">非法出售用于骗取出口退税、抵扣税款发票罪,骗取出口退税罪</WFQKGY><P_INDEX name=\"案发区域代码：《接口定义说明》附录9\">110000</P_INDEX><P_NAME name=\"案发区域名称：《接口定义说明》附录9\">北京市</P_NAME><CASE_OCCUR_DATE name=\"案发时间：格式 yyyy-MM-dd\">2012-11-28</CASE_OCCUR_DATE><WF_MONEY name=\"涉案金额\">100.22</WF_MONEY><CASE_CC_DATE name=\"立案时间：格式 yyyy-MM-dd\">2012-11-30</CASE_CC_DATE><CFXX name=\"处罚信息\">罚款100元人民币</CFXX><XZAJH name=\"行政案号\">皖A100S-XXXX</XZAJH><CF_DATE name=\"处罚时间：格式 yyyy-MM-dd\">2012-11-30</CF_DATE><CF_TYPES name=\"处罚种类：种类ID以逗号分隔\">840</CF_TYPES><CFYJ name=\"处罚依据\">中华人民共和国XXX号文件</CFYJ><CFJDS name=\"处罚决定书\">XXXXXXXXXXXXX</CFJDS><CF_RESULT name=\"处罚结果\">罚款100元人民币</CF_RESULT><DEPT_ID name=\"上报部门ID：《接口定义说明》附录8\">848</DEPT_ID><DEPT_NAME name=\"上报部门名称：《接口定义说明》附录8\">文化部</DEPT_NAME><ORDER_VALUE name=\"排序值\">0</ORDER_VALUE></CASE_XZCF><CASE_PERSONS><CASE_PERSON_SAXX name=\"涉案人员列表\"><NAME name=\"姓名\">接口：张三1</NAME></CASE_PERSON_SAXX><CASE_PERSON_SAXX name=\"涉案人员列表\"><NAME name=\"姓名\">接口：张三2</NAME></CASE_PERSON_SAXX></CASE_PERSONS><INVOLE_CASE_UNITS><INVOLE_CASE_UNIT_SAXX name=\"涉案单位列表\"><UNIT_NAME name=\"单位名称\">test</UNIT_NAME></INVOLE_CASE_UNIT_SAXX><INVOLE_CASE_UNIT_SAXX name=\"涉案单位列表\"><UNIT_NAME name=\"单位名称\">test1</UNIT_NAME></INVOLE_CASE_UNIT_SAXX></INVOLE_CASE_UNITS></Case>");// 加密字符串,返回String的密文
      System.out.println(strEnc);
      String strDes = decryptString(strEnc);// 把String 类型的密文解密
      System.out.println(strDes);
   }
}