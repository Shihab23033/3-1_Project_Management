import java.util.Scanner;

public class MainParkingTest {
    public static void main(String[] args) {
        ParkingPool pool = new ParkingPool();

        // Start 3 parking agents
        new ParkingAgent("Agent-A", pool).start();
        new ParkingAgent("Agent-B", pool).start();
        new ParkingAgent("Agent-C", pool).start();

        Scanner scanner = new Scanner(System.in);
        int carCount = 0;

        // Simulate car arrivals
        while (carCount < 10) {
            System.out.println("Press Enter to register a new car... or type -1 to exit");
            String input = scanner.nextLine();
            if (input.equals("-1")) break;

            RegistrarParking car = new RegistrarParking();
            pool.addCar(car);
            carCount++;
        }

        scanner.close();
    }
}