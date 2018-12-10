import java.util.ArrayList;

/**
 * @author Jaewook Lee, Daniel Hooks
 * @version 12/9/18
 */

public class Drone extends Vehicle implements Profitable {

    final private double gasRate = 1.33;

    public Drone() {

    }


    public Drone(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }


    @Override
    public double getProfit() {
        double summation = 0;
        for (int i = 0; i < getPackages().size(); i++) {
            summation += getPackages().get(i).getPrice();
        }
        return summation - (getMaximumRange() * gasRate);
    }


    @Override
    public String report() {
        return "==========Drone Report==========\n" +
                "License Plate No.: " + super.getLicensePlate() + '\n' +
                "Destination: " + super.getZipDest() + "\n" +
                "Weight Load: " + super.getCurrentWeight() + "/" + super.getMaxWeight() + "\n" +
                "Net Profit: " + getProfit() + "\n" +
                "==============================";
    }


}
