package cn.ltx.springboot.utils;

import cn.ltx.springboot.constant.NumberConstant;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description: 身份证工具类
 *
 * @author litianxiang
 * @date 2019/1/22
 */
public class IDCardUtil {
    /**
     * Properties默认编码是iso8859-1
     */
    private static Properties props = new Properties();
    /**
     * classpath目录下的IDCardAddressCode.properties
     */
    private static String filePath = "/IDCardAddressCode.properties";

    static {
        InputStream in = IDCardUtil.class.getResourceAsStream(filePath);
        try {
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成方法
     *
     * @return
     */
    public static String generate() {
        StringBuilder generator = new StringBuilder();
        generator.append(randomAreaCode());
        generator.append(randomBirthday());
        generator.append(randomCode());
        generator.append(calcTrailingNumber(generator.toString().toCharArray()));
        return generator.toString();
    }

    /**
     * 随机地区
     *
     * @return
     */
    private static String randomAreaCode() {
        int index = (int) (Math.random() * props.stringPropertyNames().size());
        Collection<String> values = props.stringPropertyNames();
        Iterator<String> it = values.iterator();
        int i = 0;
        String code = "";
        while (i < index && it.hasNext()) {
            i++;
            code = it.next();
        }
        return code;
    }

    /**
     * 随机出生日期
     *
     * @return
     */
    private static String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, new Random().nextInt(60) + 1950);
        birthday.set(Calendar.MONTH, new Random().nextInt(12) + 1);
        birthday.set(Calendar.DATE, new Random().nextInt(31) + 1);

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < NumberConstant.TEN) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < NumberConstant.TEN) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /**
     * 18位身份证验证<br>
     * 根据〖中华人民共和国国家标准 GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。<br>
     * 第十八位数字(校验码)的计算方法为：<br>
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 <br>
     * 2.将这17位数字和系数相乘的结果相加。<br>
     * 3.用加出来和除以11，看余数是多少？<br>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。<br>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。<br>
     */
    private static char calcTrailingNumber(char[] chars) {
        if (chars.length < NumberConstant.SEVENTEEN) {
            return ' ';
        }
        int[] c = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] r = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }

    /**
     * 随机产生3位数
     *
     * @return
     */
    private static String randomCode() {
        int code = new Random().nextInt(1000);
        if (code < NumberConstant.TEN) {
            return "00" + code;
        } else if (code < NumberConstant.ONEHUNDRED) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }

    /**
     * 方法名：parseGender
     * 详述：根据所传身份证号解析其性别
     * 开发人员：souvc
     * 创建时间：2015-9-7 下午1:55:44
     *
     * @param cid 身份证号
     * @return 说明返回值含义
     * @throw 说明发生此异常的条件
     */
    public static String parseGender(String cid) {
        char c = cid.charAt(cid.length() - 2);
        int sex = Integer.parseInt(String.valueOf(c));
        if (sex % NumberConstant.TWO == 0) {
            return "女";
        } else {
            return "男";
        }
    }

    /**
     * 方法名：checkCardId
     * 详述：检测身份证号是否符合规则
     * 校验规则：
     * 1、将前面的身份证号码17位数分别乘以不同的系数。第i位对应的数为[2^(18-i)]mod11。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 ；
     * 2、将这17位数字和系数相乘的结果相加；
     * 3、用加出来和除以11，看余数是多少？；
     * 4、余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2；
     *
     * @param cid
     * @return boolean说明返回值含义
     */
    public static boolean checkCardId(String cid) {
        boolean flag = false;
        int len = cid.length();
        int kx = 0;
        //身份证号第一位到第十七位的系数装入到一个整型数组
        int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        //需要进行运算的是身份证前17位
        for (int i = 0; i < len - 1; i++) {
            //把身份证的数字分拆成一个个数字
            int x = Integer.parseInt(String.valueOf(cid.charAt(i)));
            //然后相加起来
            kx += weight[i] * x;
        }
        //用加出来和模以11，看余数是多少？
        int mod = kx % 11;
        //最后一位身份证的号码的对应号码,一一对应
        //(0,1,2,3,4,5,6,7,8,9,10)
        //(1,0,X,9,8,7,6,5,4,3,2)
        Character[] checkMods = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        //获取身份证最后的一个验证码
        Character lastCode = cid.charAt(len - 1);
        //判断是否对应
        String idNumber = lastCode.toString().toLowerCase();
        String checkMods2 = checkMods[mod].toString().toLowerCase();
        if (checkMods2.equals(idNumber)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 方法名：parseAge
     * 详述：根据身份证号码，返回年龄
     * 开发人员：souvc
     * 创建时间：2015-9-7 下午1:56:15
     *
     * @param cid 身份证号
     * @return 说明返回值含义
     * @throw 说明发生此异常的条件
     */
    public static int parseAge(String cid) {
        int age = 0;
        String birthDayStr = cid.substring(6, 14);
        Date birthDay = null;
        try {
            birthDay = new SimpleDateFormat("yyyyMMdd").parse(birthDayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("您还没有出生么？");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth && dayNow < dayBirth) {
                age--;
            }
        } else {
            age--;
        }
        return age;
    }

    /**
     * 方法名：parseAddress
     * 详述：通过身份证号码解析其出生所在地
     * 开发人员：souvc
     * 创建时间：2015-9-7 下午1:56:49
     *
     * @param cid 身份证号
     * @return 说明返回值含义
     * @throw 说明发生此异常的条件
     */
    public static String parseAddress(String cid) {
        String address = null;
        String addressCode = cid.substring(0, 6);
        try {
            address = new String(props.get(addressCode).toString().getBytes("utf-8"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * 方法名：parseBirthday
     * 详述：根据身份证号截取出生日期
     * 开发人员：liuhf
     * 创建时间：2015-9-7 下午2:08:20
     *
     * @param cid 身份证号
     * @return 说明返回值含义
     * @throw 说明发生此异常的条件
     */
    public static String parseBirthday(String cid) {
        //通过身份证号来读取出生日期
        String birthday = "";
        //如果没有身份证，那么不进行字符串截取工作。
        if (checkCardId(cid)) {
            String year = cid.substring(6, 10);
            String month = cid.substring(10, 12);
            String day = cid.substring(12, 14);
            birthday = year + "-" + month + "-" + day;
        }
        return birthday;
    }

    public static void printf(String idCardNo,int minAge) {
        if (IDCardUtil.parseAge(idCardNo) > minAge) {
            System.out.print(idCardNo + " | ");
            System.out.print(IDCardUtil.checkCardId(idCardNo) + " | ");
            System.out.printf("%5s", IDCardUtil.parseAge(idCardNo) + " | ");
            System.out.print(IDCardUtil.parseBirthday(idCardNo) + " | ");
            System.out.print(IDCardUtil.parseGender(idCardNo) + " | ");
            System.out.print(IDCardUtil.parseAddress(idCardNo));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            printf(generate(),18);
        }
    }
}