class Car {
    String model;
    void show() {
        System.out.println(model);
    }
}

public class ClassesAndObjects {
    public static void main(String[] args) {
        Car c = new Car();
        c.model = "Toyota";
        c.show();
    }
}
