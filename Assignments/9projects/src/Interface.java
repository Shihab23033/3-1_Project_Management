interface Animal {
    void sound();
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }
}

public class Interface {
    public static void main(String[] args) {
        Animal d = new Dog();
        d.sound();
    }
}
