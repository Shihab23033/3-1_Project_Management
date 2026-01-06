import java.util.Scanner;

public class ATM {
    private double balance = 500;

    void addMoney(double amount) {
        balance += amount;
        System.out.println("Updated Balance: " + balance);
    }

    void takeMoney(double amount) {
        if(amount > balance) {
            System.out.println("Not enough funds");
        } else {
            balance -= amount;
            System.out.println("Updated Balance: " + balance);
        }
    }

    void showBalance() {
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM a = new ATM();

        while(true) {
            System.out.println("1.Add 2.Withdraw 3.Show 4.Quit");
            int c = sc.nextInt();

            if(c == 1) {
                double amt = sc.nextDouble();
                a.addMoney(amt);
            } else if(c == 2) {
                double amt = sc.nextDouble();
                a.takeMoney(amt);
            } else if(c == 3) {
                a.showBalance();
            } else if(c == 4) {
                break;
            }
        }
    }
}
