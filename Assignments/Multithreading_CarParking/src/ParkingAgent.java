public class ParkingAgent extends Thread {
    private final String agentName;
    private final ParkingPool pool;

    public ParkingAgent(String agentName, ParkingPool pool) {
        this.agentName = agentName;
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            RegistrarParking car = pool.getCar();
            System.out.println(agentName + " is parking " + car);
            try {
                Thread.sleep(1000); // Simulate time to park
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}