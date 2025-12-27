import java.util.*;

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ")";
    }
}

public class StudentDetailsTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, Student> studentMap = new TreeMap<>();

        studentMap.put(101, new Student("Alice", 20));
        studentMap.put(102, new Student("Bob", 22));
        studentMap.put(103, new Student("Charlie", 19));

        System.out.println("Student Details:");
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}