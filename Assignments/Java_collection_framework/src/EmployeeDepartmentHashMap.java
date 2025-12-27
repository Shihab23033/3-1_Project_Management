import java.util.*;

public class EmployeeDepartmentHashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> employeeMap = new HashMap<>();

        employeeMap.put(1001, "HR");
        employeeMap.put(1002, "Finance");
        employeeMap.put(1003, "IT");
        employeeMap.put(1004, "Marketing");

        System.out.println("Employee Departments:");
        for (Map.Entry<Integer, String> entry : employeeMap.entrySet()) {
            System.out.println("Employee ID: " + entry.getKey() + " -> Department: " + entry.getValue());
        }
    }
}