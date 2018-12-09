import java.util.ArrayList;

/**
 * <h1>Vehicle</h1> Represents a vehicle
 */

public class Vehicle {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private int maximumRange;
    private ArrayList<Package> packages;


    public Vehicle() {
        licensePlate = "";
        maxWeight = 0.0;
        currentWeight = 0.0;
        zipDest = 0;
        packages = new ArrayList<>();
        maximumRange = 0;
    }

    public Vehicle(String licensePlate, double maxWeight) {
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
        currentWeight = 0.0;
        zipDest = 0;
        packages = new ArrayList<>();
        maximumRange = 0;
    }

    public String getLicensePlate() {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    public double getMaxWeight() {
        return maxWeight;
    }


    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }


    public double getCurrentWeight() {
        return currentWeight;
    }


    public int getZipDest() {
        return zipDest;
    }


    public void setZipDest(int zipDest) {
        this.zipDest = zipDest;
    }


    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setMaximumRange(int maximumRange) {
        this.maximumRange = maximumRange;
    }

    public int getMaximumRange() {
        return maximumRange;
    }


    public boolean addPackage(Package pkg) {
        if (getCurrentWeight() + pkg.getWeight() <= getMaxWeight()) {
            packages.add(pkg);
            currentWeight += pkg.getWeight();
            return true;
        } else {
            return false;
        }
    }


    public void empty() {
        for (int i = packages.size() - 1; i >= 0; i--) {
            if (packages.get(i) != null) {
                packages.remove(i);
            }
        }
        currentWeight = 0;
    }


    public boolean isFull() {
        for (int i = 0; i < packages.size(); i++) {
            if (packages.get(i).getWeight() + currentWeight > maxWeight) {
                return true;
            }
        }
        return false;

    }

    public void fill(ArrayList<Package> warehousePackages) {
        int range = 0;
        int distance = 0;
        boolean loop = true;
        while (loop) {
            if (!isFull() && warehousePackages.size() != 0) {
                for (int i = 0; i < warehousePackages.size(); i++) {
                    distance = zipDest - warehousePackages.get(i).getDestination().getZipCode();
                    if (Math.abs(distance) == range) {
                        if (addPackage(warehousePackages.get(i))) {
                            if (Math.abs(distance) > maximumRange) {
                                maximumRange = Math.abs(distance);
                            }
                        } else {
                            break;
                        }
                    }
                    range++;
                }
                break;
            } else {
                loop = false;
            }
        }
    }

    public double getProfit() {
        return 0.0;
    }

    public String report() {
        return "";
    }
}


