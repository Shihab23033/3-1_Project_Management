class A {
    protected void display() {
        System.out.println("Hello");
    }
}

class B extends A {
    void show() {
        display();
    }
}

public class Inheritance {
    public static void main(String[] args) {
        B b = new B();
        b.show();
    }
}
