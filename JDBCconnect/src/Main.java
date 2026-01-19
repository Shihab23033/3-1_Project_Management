import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Welcome to Student Vlog system");
        while(true) {
            System.out.println("Options:");
            System.out.println("1: insert a vlog");
            System.out.println("2: Show vlogs");
            System.out.println("3: Exit");
            int choice=sc.nextInt();
            sc.nextLine();

            if(choice==3) break;
            else if(choice==1){
                System.out.println("enter the name of the student");
                String nm= sc.nextLine();
                System.out.println("write your vlog");
                String stmnt= sc.nextLine();

                vlog v= new vlog(nm,stmnt);
                try{
                    serviceClass services=new serviceClass();
                    services.getConnection();
                    services.insert_quire(v);
                    services.closeAll();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(choice==2){
                List<vlog> list =new serviceClass().showResults();
                System.out.println("NAME -> Statement");
                for(vlog v :list){
                    System.out.println(v.getName()+" -> "+v.getStatement());
                }
            }
        }

        sc.close();

    }
}