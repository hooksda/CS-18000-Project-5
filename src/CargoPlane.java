import java.util.ArrayList;


/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle implements Profitable {
    final double GAS_RATE = 2.33;

    /**
     * Default Constructor
     */
    public CargoPlane() {
    }

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    public CargoPlane(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
    public void fill(ArrayList<Package> warehousePackages) {
        int distance = 0;
        boolean loop = true;
        while (loop) {
            if (!isFull() && warehousePackages.size() != super.getPackages().size()) {
                for (int i = 0; i < warehousePackages.size(); i++) {
                    distance = super.getZipDest() - warehousePackages.get(i).getDestination().getZipCode();
                    if (Math.abs(distance) == getZipDest()) {
                        if (addPackage(warehousePackages.get(i))) {
                            distance = super.getMaximumRange();
                        }
                    }
                    distance = (distance + 1) * 10;
                }
            } else {
                loop = false;
            }
        }
    }



    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */
    @Override
    public double getProfit() {
        double summation = 0;
        for (int i = 0; i < super.getPackages().size(); i++) {
            summation += super.getPackages().get(i).getPrice();
        }
        return summation - (super.getMaximumRange() * GAS_RATE);
    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {
        return "==========Truck Report==========\n" +
                "License Plate No.: " + super.getLicensePlate() + '\n' +
                "Destination: " + super.getZipDest() + "\n" +
                "Weight Load: " + super.getCurrentWeight() + "/" + super.getMaxWeight() + "\n" +
                "Net Profit: " + getProfit() + "\n" +
                "==============================";
    }
}


