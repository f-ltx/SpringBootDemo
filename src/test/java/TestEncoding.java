import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

public class TestEncoding {

    /**
     * Description: UTF-8中，一个汉字3个字节，GBK中一个汉字2个字节
     *
     * @author  litianxiang
     * @date    2019/1/8 12:30
     *
     */
    public static void main(String[] args) throws Exception {
        String good = "我很好";
        String bad = "我很不好" ;
        System.out.println("默认:\t"+Arrays.toString(good.getBytes()));
        System.out.println("GBK：\t"+Arrays.toString(good.getBytes("GBK")));
        System.out.println("UTF-8：\t"+Arrays.toString(good.getBytes("UTF-8")));
        System.out.println("默认:\t"+Arrays.toString(bad.getBytes()));
        System.out.println("GBK：\t"+Arrays.toString(bad.getBytes("GBK")));
        System.out.println("UTF-8：\t"+Arrays.toString(bad.getBytes("UTF-8")));
        System.out.println("============================================================");
        System.out.println(new String(good.getBytes("GBK")));
        System.out.println(new String(good.getBytes("GBK"),"GBK"));
        System.out.println(new String(good.getBytes("UTF-8")));
        System.out.println(new String(good.getBytes("UTF-8"),"UTF-8"));
        System.out.println("============================================================");
        System.out.println(new String(bad.getBytes("GBK")));
        System.out.println(new String(bad.getBytes("GBK"),"GBK"));
        System.out.println(new String(bad.getBytes("UTF-8")));
        System.out.println(new String(bad.getBytes("UTF-8"),"UTF-8"));
    }
}
