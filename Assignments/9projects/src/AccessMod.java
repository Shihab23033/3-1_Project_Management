class X {
    public int x = 10;
    private int y = 20;
    protected int z = 30;
}

public class AccessMod {
    public static void main(String[] args) {
        X a = new X();
        System.out.println(a.x);
        System.out.println(a.z);
    }
}
