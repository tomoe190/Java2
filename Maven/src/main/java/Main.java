public class Main {
    public static void main(String[] args) {
//        System.out.println("you are very good");
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operate(a,b);
        System.out.println(a + "." + b);
    }

    public static void operate(StringBuffer x,StringBuffer y) {
        x.append(y);
        y = x;
    }
}
