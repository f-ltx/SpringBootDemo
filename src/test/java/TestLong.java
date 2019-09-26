public class TestLong {
    private volatile int x ;
    private volatile long y;
    volatile String z;
    public static void main(String[] args) {
        Long x = 9901L;
        if("9902".equals(x.toString())){
            System.out.println(x);
        }else {
            System.out.println("xxxx");
        }
    }
}
