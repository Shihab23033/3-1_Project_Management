import java.util.LinkedList;
import java.util.Queue;

public class ParkingPool {
    private final Queue<RegistrarParking> parkingQueue = new LinkedList<>();

    // Add a car to the parking queue
    public synchronized void addCar(RegistrarParking car) {
        parkingQueue.add(car);
        System.out.println(car + " arrived and waiting to park.");
        notifyAll(); // Notify agents that a car is waiting
    }

    // Retrieve a car from the queue
    public synchronized RegistrarParking getCar() {
        while (parkingQueue.isEmpty()) {
            try {
                wait(); // Wait until a car arrives
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return parkingQueue.poll();
    }
}