import java.util.ArrayList;


/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle implements Profitable {
    final double GAS_RATE = 2.33;

    public CargoPlane() {
    }

    public CargoPlane(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }

    @Override
    public void fill(ArrayList<Package> warehousePackages) {
        int range = 0;
        int distance = 0;
        boolean loop = true;
        while (loop) {
            if (!isFull() && warehousePackages.size() != 0) {
                for (int i = 0; i < warehousePackages.size(); i++) {
                    distance = getZipDest() - warehousePackages.get(i).getDestination().getZipCode();
                    if (Math.abs(distance) == range) {
                        if (addPackage(warehousePackages.get(i))) {
                           distance = getMaximumRange();
                        } else {
                            loop = false;
                        }
                    }
                }
                range += 10;
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

    @Override
    public double getProfit() {
        double summation = 0;
        for (int i = 0; i < super.getPackages().size(); i++) {
            summation += super.getPackages().get(i).getPrice();
        }
        return summation - (super.getMaximumRange() * GAS_RATE);
    }

    @Override
    public String report() {
        return "==========Plane Report==========\n" +
                "License Plate No.: " + super.getLicensePlate() + '\n' +
                "Destination: " + super.getZipDest() + "\n" +
                "Weight Load: " + super.getCurrentWeight() + "/" + super.getMaxWeight() + "\n" +
                "Net Profit: " + getProfit() + "\n" +
                "==============================";
    }
}


