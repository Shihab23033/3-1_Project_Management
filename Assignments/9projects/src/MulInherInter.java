interface C {
    void showA();
}

interface D {
    void showB();
}

class E implements C, D {
    public void showA() { System.out.println("A"); }
    public void showB() { System.out.println("B"); }
}

public class MulInherInter {
    public static void main(String[] args) {
        E c = new E();
        c.showA();
        c.showB();
    }
}
