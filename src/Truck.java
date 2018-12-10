import java.util.ArrayList;


/**
 * <h1>Truck</h1> Represents a Truck
 */
public class Truck extends Vehicle implements Profitable {

    private final double gasRate = 1.66;

    public Truck() {

    }


    public Truck(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }


    @Override
    public double getProfit() {
        double summation = 0;
        for (int i = 0; i < super.getPackages().size(); i++) {
            summation += super.getPackages().get(i).getPrice();
        }
        return summation - (super.getMaximumRange() * gasRate);
    }


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




