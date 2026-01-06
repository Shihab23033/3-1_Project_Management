import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        char op = sc.next().charAt(0);

        double ans = 0;

        if(op == '+') ans = a + b;
        else if(op == '-') ans = a - b;
        else if(op == '*') ans = a * b;
        else if(op == '/') {
            if(b != 0) ans = a / b;
            else System.out.println("Zero not allowed");
        } else {
            System.out.println("Wrong choice");
        }

        System.out.println(ans);
    }
}
